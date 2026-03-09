package com.fees.management.repository;

import com.fees.management.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findBySchoolId(Long schoolId);
    List<Exam> findByCourseIdAndSchoolId(Long courseId, Long schoolId);
}
