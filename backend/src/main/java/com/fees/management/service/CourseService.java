package com.fees.management.service;

import com.fees.management.dto.CourseRequestDto;
import com.fees.management.dto.CourseResponseDto;
import com.fees.management.entity.Course;

import java.util.List;

public interface CourseService {
    CourseResponseDto saveCourse(CourseRequestDto dto, Long schoolId);
    List<Course> getAllCourses(Long schoolId);
    Course getCourseById(Long id, Long schoolId);

    List<CourseResponseDto> getAllCourseDtos(Long schoolId);

    CourseResponseDto updateCourse(Long id, CourseRequestDto dto, Long schoolId);

}
