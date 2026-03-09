package com.fees.management.repository;

import com.fees.management.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findBySchoolId(Long schoolId);
    List<Vehicle> findByRouteIdAndSchoolId(Long routeId, Long schoolId);
}
