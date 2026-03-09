package com.fees.management.controller;

import com.fees.management.dto.CourseRequestDto;
import com.fees.management.dto.CourseResponseDto;
import com.fees.management.entity.Course;
import com.fees.management.entity.User;
import com.fees.management.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponseDto createCourse(@Valid @RequestBody CourseRequestDto course,
                                          @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return courseService.saveCourse(course, schoolId);
    }

    @GetMapping
    public List<CourseResponseDto> getAllCourses(@AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return courseService.getAllCourseDtos(schoolId);
    }

    @PutMapping("/{id}")
    public CourseResponseDto updateCourse(@PathVariable Long id,
                                          @RequestBody CourseRequestDto dto,
                                          @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return courseService.updateCourse(id, dto, schoolId);
    }

}
