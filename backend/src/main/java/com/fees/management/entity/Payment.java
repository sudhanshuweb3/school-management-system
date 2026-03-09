package com.fees.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @NotNull(message = "Payment date required")
    private LocalDate paymentDate;

    @NotBlank(message = "Mode required")
    private String mode;

    @ManyToOne
    @JoinColumn(name = "student_id")
    //@JsonIgnoreProperties("payments") // prevents infinite loop
    private Student student;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    // ===== Getters =====
    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getMode() {
        return mode;
    }

    public Student getStudent() {
        return student;
    }

    // ===== Setters =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
