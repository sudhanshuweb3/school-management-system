package com.fees.management.dto;

public class DashboardStatsDto {

    private long totalStudents;
    private long totalTeachers;
    private long totalCourses;
    private long totalAttendanceToday;

    public DashboardStatsDto(long totalStudents, long totalTeachers, long totalCourses, long totalAttendanceToday) {
        this.totalStudents = totalStudents;
        this.totalTeachers = totalTeachers;
        this.totalCourses = totalCourses;
        this.totalAttendanceToday = totalAttendanceToday;
    }

    public long getTotalStudents() { return totalStudents; }
    public long getTotalTeachers() { return totalTeachers; }
    public long getTotalCourses() { return totalCourses; }
    public long getTotalAttendanceToday() { return totalAttendanceToday; }
}
