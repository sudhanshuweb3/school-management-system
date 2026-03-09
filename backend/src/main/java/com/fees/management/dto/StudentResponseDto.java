package com.fees.management.dto;

public class StudentResponseDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String courseName;

    public StudentResponseDto(Long id, String name, String email, String phone, String courseName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.courseName = courseName;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCourseName() { return courseName; }
}
