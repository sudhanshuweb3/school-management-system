package com.fees.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "transport_routes")
public class TransportRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Route name is required")
    private String routeName;

    @NotBlank(message = "Starting point is required")
    private String startingPoint;

    @NotBlank(message = "Ending point is required")
    private String endingPoint;

    private Double fare;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @OneToMany(mappedBy = "route")
    private List<Vehicle> vehicles;

    // Getters
    public Long getId() {
        return id;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public Double getFare() {
        return fare;
    }

    public School getSchool() {
        return school;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
