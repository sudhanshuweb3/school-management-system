package com.fees.management.repository;

import com.fees.management.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findBySchoolId(Long schoolId);
}
