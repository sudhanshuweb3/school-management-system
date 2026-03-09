package com.fees.management.dto;

public class AttendanceResponseDto {

    private Long id;
    private Long studentId;
    private String studentName;
    private String date;
    private String status;

    public AttendanceResponseDto(Long id, Long studentId, String studentName, String date, String status) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.status = status;
    }

    public Long getId() { return id; }
    public Long getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
}
