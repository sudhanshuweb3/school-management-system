package com.fees.management.service;

import com.fees.management.dto.AttendanceRequestDto;
import com.fees.management.dto.AttendanceResponseDto;
import com.fees.management.entity.Attendance;
import com.fees.management.entity.AttendanceStatus;
import com.fees.management.entity.School;
import com.fees.management.entity.Student;
import com.fees.management.exception.ResourceNotFoundException;
import com.fees.management.repository.AttendanceRepository;
import com.fees.management.repository.SchoolRepository;
import com.fees.management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             StudentRepository studentRepository,
                             SchoolRepository schoolRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    public AttendanceResponseDto markAttendance(AttendanceRequestDto dto, Long schoolId) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School not found"));

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setDate(LocalDate.parse(dto.getDate()));
        attendance.setStatus(AttendanceStatus.valueOf(dto.getStatus().toUpperCase()));
        attendance.setSchool(school);

        Attendance saved = attendanceRepository.save(attendance);
        return toDto(saved);
    }

    public List<AttendanceResponseDto> getAllAttendance(Long schoolId) {
        return attendanceRepository.findBySchoolId(schoolId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponseDto> getAttendanceByStudent(Long studentId, Long schoolId) {
        return attendanceRepository.findByStudentIdAndSchoolId(studentId, schoolId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponseDto> getAttendanceByDate(String date, Long schoolId) {
        LocalDate localDate = LocalDate.parse(date);
        return attendanceRepository.findByDateAndSchoolId(localDate, schoolId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public long countTodayAttendance(Long schoolId) {
        return attendanceRepository.countByDateAndSchoolId(LocalDate.now(), schoolId);
    }

    private AttendanceResponseDto toDto(Attendance a) {
        return new AttendanceResponseDto(
                a.getId(),
                a.getStudent().getId(),
                a.getStudent().getName(),
                a.getDate().toString(),
                a.getStatus().name()
        );
    }
}
