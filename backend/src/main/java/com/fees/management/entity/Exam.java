package com.fees.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Exam name is required")
    private String name;

    @NotNull(message = "Exam date is required")
    private LocalDate examDate;

    private String subject;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "exam")
    private List<Result> results;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public String getSubject() {
        return subject;
    }

    public School getSchool() {
        return school;
    }

    public Course getCourse() {
        return course;
    }

    public List<Result> getResults() {
        return results;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
