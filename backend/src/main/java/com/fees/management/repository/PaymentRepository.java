package com.fees.management.repository;

import com.fees.management.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentId(Long studentId);
    List<Payment> findByStudentIdAndSchoolId(Long studentId, Long schoolId);
    List<Payment> findBySchoolId(Long schoolId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Payment p WHERE p.student.id = :studentId")
    void deleteByStudentId(Long studentId);
}
