package com.fees.management.dto;

import java.time.LocalDate;

public class PaymentResponseDto {

    private Long id;
    private Double amount;
    private LocalDate paymentDate;
    private String mode;
    private Long studentId;
    private String studentName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
}
