package com.fees.management.mapper;

import com.fees.management.dto.StudentResponseDto;
import com.fees.management.entity.Student;

public class StudentMapper {

    private StudentMapper() {}

    public static StudentResponseDto toDto(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getCourse().getName()
        );
    }
}
