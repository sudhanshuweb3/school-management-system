package com.fees.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "course_id")
    //@JsonIgnoreProperties("students") // prevents infinite loop
    private Course course;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "student")
    private List<Payment> payments;

    // ===== Getters =====
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Course getCourse() {
        return course;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    // ===== Setters =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
