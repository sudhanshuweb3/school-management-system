package com.fees.management.dto;

public class FeeSummaryResponse {

    private Long studentId;
    private String name;
    private double totalFee;
    private double paid;
    private double remaining;

    public FeeSummaryResponse(Long studentId, String name, double totalFee, double paid, double remaining) {
        this.studentId = studentId;
        this.name = name;
        this.totalFee = totalFee;
        this.paid = paid;
        this.remaining = remaining;
    }
    public Long getStudentId() { return studentId; }
    public String getName() { return name; }
    public double getTotalFee() { return totalFee; }
    public double getPaid() { return paid; }
    public double getRemaining() { return remaining; }
}
