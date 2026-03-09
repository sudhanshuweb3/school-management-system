package com.fees.management.repository;

import com.fees.management.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudentIdAndSchoolId(Long studentId, Long schoolId);
    List<Result> findByExamIdAndSchoolId(Long examId, Long schoolId);
    List<Result> findBySchoolId(Long schoolId);
}
