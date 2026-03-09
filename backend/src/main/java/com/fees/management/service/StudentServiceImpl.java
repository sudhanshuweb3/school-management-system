package com.fees.management.service;

import com.fees.management.dto.FeeSummaryResponse;
import com.fees.management.dto.StudentRequestDto;
import com.fees.management.dto.StudentResponseDto;
import com.fees.management.entity.Course;
import com.fees.management.entity.Payment;
import com.fees.management.entity.Student;
import com.fees.management.exception.ResourceNotFoundException;
import com.fees.management.mapper.StudentMapper;
import com.fees.management.entity.School;
import com.fees.management.repository.CourseRepository;
import com.fees.management.repository.PaymentRepository;
import com.fees.management.repository.SchoolRepository;
import com.fees.management.repository.StudentRepository;
import com.fees.management.service.StudentService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);


    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final PaymentRepository paymentRepository;
    private final SchoolRepository schoolRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              CourseRepository courseRepository,
                              PaymentRepository paymentRepository,
                              SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.paymentRepository = paymentRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto request, Long schoolId) {

        log.info("Creating student with email: {} for school: {}", request.getEmail(), schoolId);

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> {
                    log.error("Course not found with id: {}", request.getCourseId());
                    return new ResourceNotFoundException("Course not found");
                });

        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCourse(course);
        student.setSchool(school);

        Student saved = studentRepository.save(student);

        log.info("Student created successfully with id: {}", saved.getId());

        return StudentMapper.toDto(saved);
    }


    @Override
    public List<StudentResponseDto> getAllStudents(Long schoolId) {
        return studentRepository.findBySchoolId(schoolId)
                .stream()
                .map(StudentMapper::toDto)
                .toList();
    }

    @Override
    public StudentResponseDto getStudentById(Long id, Long schoolId) {

        log.info("Fetching student with id: {} for school: {}", id, schoolId);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student not found with id: {}", id);
                    return new ResourceNotFoundException("Student not found");
                });

        // Verify student belongs to the school
        if (!student.getSchool().getId().equals(schoolId)) {
            throw new ResourceNotFoundException("Student not found in this school");
        }

        return StudentMapper.toDto(student);
    }


    @Override
    public void deleteStudent(Long id, Long schoolId) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // Verify student belongs to the school
        if (!student.getSchool().getId().equals(schoolId)) {
            throw new ResourceNotFoundException("Student not found in this school");
        }

        // delete all payments of this student first
        paymentRepository.deleteByStudentId(id);

        // then delete student
        studentRepository.delete(student);

        log.info("Student deleted successfully: {}", id);
    }


    @Override
    public FeeSummaryResponse getFeeSummary(Long studentId, Long schoolId) {

        log.info("Fetching fee summary for student: {} in school: {}", studentId, schoolId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    log.error("Student not found for summary: {}", studentId);
                    return new ResourceNotFoundException("Student not found");
                });

        // Verify student belongs to the school
        if (!student.getSchool().getId().equals(schoolId)) {
            throw new ResourceNotFoundException("Student not found in this school");
        }

        double totalFee = student.getCourse().getTotalFee();

        List<Payment> payments = paymentRepository.findByStudentIdAndSchoolId(studentId, schoolId);
        double paid = payments.stream().mapToDouble(Payment::getAmount).sum();

        double remaining = totalFee - paid;

        log.info("Fee summary -> total: {}, paid: {}, pending: {}", totalFee, paid, remaining);

        return new FeeSummaryResponse(
                student.getId(),
                student.getName(),
                totalFee,
                paid,
                remaining
        );
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto request, Long schoolId) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // Verify student belongs to the school
        if (!student.getSchool().getId().equals(schoolId)) {
            throw new ResourceNotFoundException("Student not found in this school");
        }

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCourse(course);

        Student updated = studentRepository.save(student);

        return StudentMapper.toDto(updated);
    }



}
