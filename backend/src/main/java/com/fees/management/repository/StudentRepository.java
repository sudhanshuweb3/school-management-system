package com.fees.management.repository;

import com.fees.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findBySchoolId(Long schoolId);
    List<Student> findByCourseIdAndSchoolId(Long courseId, Long schoolId);
    long countBySchoolId(Long schoolId);
}
