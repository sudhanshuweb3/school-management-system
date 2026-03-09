package com.fees.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vehicle number is required")
    private String vehicleNumber;

    private String vehicleType; // Bus, Van, etc.

    private Integer capacity;

    private String driverName;

    private String driverContact;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private TransportRoute route;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    // Getters
    public Long getId() {
        return id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public TransportRoute getRoute() {
        return route;
    }

    public School getSchool() {
        return school;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

    public void setRoute(TransportRoute route) {
        this.route = route;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
