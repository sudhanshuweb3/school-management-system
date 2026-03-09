package com.fees.management.controller;

import com.fees.management.entity.TransportRoute;
import com.fees.management.entity.User;
import com.fees.management.entity.Vehicle;
import com.fees.management.service.TransportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transport")
public class TransportController {

    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    // Route endpoints
    @PostMapping("/routes")
    public ResponseEntity<TransportRoute> createRoute(@RequestBody TransportRoute route, @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        TransportRoute created = transportService.createRoute(route, schoolId);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/routes")
    public ResponseEntity<List<TransportRoute>> getRoutes(@AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        List<TransportRoute> routes = transportService.getRoutesBySchool(schoolId);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<TransportRoute> getRoute(@PathVariable Long id) {
        TransportRoute route = transportService.getRouteById(id);
        return ResponseEntity.ok(route);
    }

    @PutMapping("/routes/{id}")
    public ResponseEntity<TransportRoute> updateRoute(@PathVariable Long id, @RequestBody TransportRoute route) {
        TransportRoute updated = transportService.updateRoute(id, route);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/routes/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        transportService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    // Vehicle endpoints
    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle, @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        Vehicle created = transportService.createVehicle(vehicle, schoolId);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicles(@AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        List<Vehicle> vehicles = transportService.getVehiclesBySchool(schoolId);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/vehicles/route/{routeId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByRoute(@PathVariable Long routeId, @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        List<Vehicle> vehicles = transportService.getVehiclesByRoute(routeId, schoolId);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
        Vehicle vehicle = transportService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle updated = transportService.updateVehicle(id, vehicle);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        transportService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
