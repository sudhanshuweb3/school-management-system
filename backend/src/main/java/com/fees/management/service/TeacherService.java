package com.fees.management.service;

import com.fees.management.dto.TeacherRequestDto;
import com.fees.management.dto.TeacherResponseDto;
import com.fees.management.entity.School;
import com.fees.management.entity.Teacher;
import com.fees.management.exception.ResourceNotFoundException;
import com.fees.management.repository.SchoolRepository;
import com.fees.management.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;

    public TeacherService(TeacherRepository teacherRepository, SchoolRepository schoolRepository) {
        this.teacherRepository = teacherRepository;
        this.schoolRepository = schoolRepository;
    }

    public TeacherResponseDto createTeacher(TeacherRequestDto dto, Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School not found"));

        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setPhone(dto.getPhone());
        teacher.setSubject(dto.getSubject());
        teacher.setSchool(school);

        Teacher saved = teacherRepository.save(teacher);
        return toDto(saved);
    }

    public List<TeacherResponseDto> getAllTeachers(Long schoolId) {
        return teacherRepository.findBySchoolId(schoolId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TeacherResponseDto getTeacherById(Long id, Long schoolId) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        if (!teacher.getSchool().getId().equals(schoolId)) {
            throw new ResourceNotFoundException("Teacher not found in your school");
        }
        return toDto(teacher);
    }

    public TeacherResponseDto updateTeacher(Long id, TeacherRequestDto dto, Long schoolId) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        if (!teacher.getSchool().getId().equals(schoolId)) {
            throw new ResourceNotFoundException("Teacher not found in your school");
        }

        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setPhone(dto.getPhone());
        teacher.setSubject(dto.getSubject());

        Teacher saved = teacherRepository.save(teacher);
        return toDto(saved);
    }

    public void deleteTeacher(Long id, Long schoolId) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        if (!teacher.getSchool().getId().equals(schoolId)) {
            throw new ResourceNotFoundException("Teacher not found in your school");
        }
        teacherRepository.delete(teacher);
    }

    public long countBySchool(Long schoolId) {
        return teacherRepository.findBySchoolId(schoolId).size();
    }

    private TeacherResponseDto toDto(Teacher t) {
        return new TeacherResponseDto(t.getId(), t.getName(), t.getEmail(), t.getPhone(), t.getSubject());
    }
}
