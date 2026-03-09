package com.fees.management.repository;

import com.fees.management.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findBySchoolId(Long schoolId);
    List<Attendance> findByStudentIdAndSchoolId(Long studentId, Long schoolId);
    List<Attendance> findByDateAndSchoolId(LocalDate date, Long schoolId);
    long countByDateAndSchoolId(LocalDate date, Long schoolId);
}
