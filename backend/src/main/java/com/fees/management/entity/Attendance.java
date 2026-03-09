package com.fees.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private AttendanceStatus status;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public AttendanceStatus getStatus() { return status; }
    public void setStatus(AttendanceStatus status) { this.status = status; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }
}
