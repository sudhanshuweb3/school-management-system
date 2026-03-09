package com.fees.management.dto;

public class CourseResponseDto {

    private Long id;
    private String name;
    private Double totalFee;
    private int totalStudents;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getTotalFee() { return totalFee; }
    public void setTotalFee(Double totalFee) { this.totalFee = totalFee; }

    public int getTotalStudents() { return totalStudents; }
    public void setTotalStudents(int totalStudents) { this.totalStudents = totalStudents; }
}
