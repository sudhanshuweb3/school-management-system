package com.fees.management.service;


import com.fees.management.dto.CourseRequestDto;
import com.fees.management.dto.CourseResponseDto;
import com.fees.management.entity.Course;
import com.fees.management.entity.School;
import com.fees.management.exception.ResourceNotFoundException;
import com.fees.management.mapper.CourseMapper;
import com.fees.management.repository.CourseRepository;
import com.fees.management.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);


    private final CourseRepository courseRepository;
    private final SchoolRepository schoolRepository;

    public CourseServiceImpl(CourseRepository courseRepository, SchoolRepository schoolRepository) {
        this.courseRepository = courseRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public CourseResponseDto saveCourse(CourseRequestDto dto, Long schoolId) {

        log.info("Creating course: {} for school: {}", dto.getName(), schoolId);

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School not found"));

        Course course = new Course();
        course.setName(dto.getName());
        course.setTotalFee(dto.getTotalFee());
        course.setSchool(school);

        Course saved = courseRepository.save(course);

        log.info("Course created with id: {}", saved.getId());

        return CourseMapper.toDto(saved);
    }


    @Override
    public List<Course> getAllCourses(Long schoolId) {
        return courseRepository.findBySchoolId(schoolId);
    }

    @Override
    public Course getCourseById(Long id, Long schoolId) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        
        if (!course.getSchool().getId().equals(schoolId)) {
            throw new ResourceNotFoundException("Course not found in this school");
        }
        
        return course;
    }

    @Override
    public List<CourseResponseDto> getAllCourseDtos(Long schoolId) {
        return courseRepository.findBySchoolId(schoolId)
                .stream()
                .map(CourseMapper::toDto)
                .toList();
    }
    private CourseResponseDto mapToResponseDto(Course course) {
        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setTotalFee(course.getTotalFee());
        dto.setTotalStudents(
                course.getStudents() == null ? 0 : course.getStudents().size()
        );
        return dto;
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto dto, Long schoolId) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (!course.getSchool().getId().equals(schoolId)) {
            throw new ResourceNotFoundException("Course not found in this school");
        }

        course.setName(dto.getName());
        course.setTotalFee(dto.getTotalFee());

        Course updated = courseRepository.save(course);

        return mapToResponseDto(updated);
    }


}
