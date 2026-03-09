package com.fees.management.dto;

import jakarta.validation.constraints.*;

public class CourseRequestDto {

    @NotBlank(message = "Course name is required")
    private String name;

    @Min(value = 1, message = "Fee must be greater than 0")
    private double totalFee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }
}
