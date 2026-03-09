package com.fees.management.repository;

import com.fees.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findBySchoolId(Long schoolId);
    long countBySchoolId(Long schoolId);
}
