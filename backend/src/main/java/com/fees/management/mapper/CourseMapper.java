package com.fees.management.mapper;

import com.fees.management.dto.CourseResponseDto;
import com.fees.management.entity.Course;

public class CourseMapper {

    private CourseMapper() {}

    public static CourseResponseDto toDto(Course course) {
        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setTotalFee(course.getTotalFee());
        dto.setTotalStudents(
                course.getStudents() == null ? 0 : course.getStudents().size()
        );
        return dto;
    }
}
