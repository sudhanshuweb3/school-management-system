package com.fees.management.service;

import com.fees.management.entity.School;
import com.fees.management.entity.TransportRoute;
import com.fees.management.entity.Vehicle;
import com.fees.management.repository.SchoolRepository;
import com.fees.management.repository.TransportRouteRepository;
import com.fees.management.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    private final TransportRouteRepository routeRepository;
    private final VehicleRepository vehicleRepository;
    private final SchoolRepository schoolRepository;

    public TransportService(TransportRouteRepository routeRepository, VehicleRepository vehicleRepository, SchoolRepository schoolRepository) {
        this.routeRepository = routeRepository;
        this.vehicleRepository = vehicleRepository;
        this.schoolRepository = schoolRepository;
    }

    // Route CRUD
    public TransportRoute createRoute(TransportRoute route, Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found"));
        route.setSchool(school);
        return routeRepository.save(route);
    }

    public List<TransportRoute> getRoutesBySchool(Long schoolId) {
        return routeRepository.findBySchoolId(schoolId);
    }

    public TransportRoute getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }

    public TransportRoute updateRoute(Long id, TransportRoute routeDetails) {
        TransportRoute route = getRouteById(id);
        route.setRouteName(routeDetails.getRouteName());
        route.setStartingPoint(routeDetails.getStartingPoint());
        route.setEndingPoint(routeDetails.getEndingPoint());
        route.setFare(routeDetails.getFare());
        return routeRepository.save(route);
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    // Vehicle CRUD
    public Vehicle createVehicle(Vehicle vehicle, Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found"));
        vehicle.setSchool(school);
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehiclesBySchool(Long schoolId) {
        return vehicleRepository.findBySchoolId(schoolId);
    }

    public List<Vehicle> getVehiclesByRoute(Long routeId, Long schoolId) {
        return vehicleRepository.findByRouteIdAndSchoolId(routeId, schoolId);
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setVehicleNumber(vehicleDetails.getVehicleNumber());
        vehicle.setVehicleType(vehicleDetails.getVehicleType());
        vehicle.setCapacity(vehicleDetails.getCapacity());
        vehicle.setDriverName(vehicleDetails.getDriverName());
        vehicle.setDriverContact(vehicleDetails.getDriverContact());
        vehicle.setRoute(vehicleDetails.getRoute());
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
