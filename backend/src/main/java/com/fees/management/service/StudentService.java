package com.fees.management.service;

import com.fees.management.dto.FeeSummaryResponse;
import com.fees.management.dto.StudentRequestDto;
import com.fees.management.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto request, Long schoolId);

    List<StudentResponseDto> getAllStudents(Long schoolId);

    StudentResponseDto getStudentById(Long id, Long schoolId);

    void deleteStudent(Long id, Long schoolId);

    FeeSummaryResponse getFeeSummary(Long studentId, Long schoolId);

    StudentResponseDto updateStudent(Long id, StudentRequestDto request, Long schoolId);

}
