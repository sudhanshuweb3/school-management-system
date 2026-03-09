package com.fees.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @Min(0)
    @Max(100)
    private Double marksObtained;

    private String grade;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    // Getters
    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Exam getExam() {
        return exam;
    }

    public Double getMarksObtained() {
        return marksObtained;
    }

    public String getGrade() {
        return grade;
    }

    public School getSchool() {
        return school;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void setMarksObtained(Double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
