package com.fees.management.controller;

import com.fees.management.entity.School;
import com.fees.management.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        School created = schoolService.createSchool(school);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        return ResponseEntity.ok(schools);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchool(@PathVariable Long id) {
        School school = schoolService.getSchoolById(id);
        return ResponseEntity.ok(school);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School school) {
        School updated = schoolService.updateSchool(id, school);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
}
