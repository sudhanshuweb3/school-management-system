package com.fees.management.repository;

import com.fees.management.entity.TransportRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRouteRepository extends JpaRepository<TransportRoute, Long> {
    List<TransportRoute> findBySchoolId(Long schoolId);
}
