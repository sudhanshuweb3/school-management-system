package com.fees.management.dto;

public class TeacherResponseDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String subject;

    public TeacherResponseDto(Long id, String name, String email, String phone, String subject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getSubject() { return subject; }
}
