package com.fees.management.service;

import com.fees.management.entity.School;
import com.fees.management.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(Long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));
    }

    public School updateSchool(Long id, School schoolDetails) {
        School school = getSchoolById(id);
        school.setName(schoolDetails.getName());
        school.setAddress(schoolDetails.getAddress());
        school.setEmail(schoolDetails.getEmail());
        school.setContactNumber(schoolDetails.getContactNumber());
        return schoolRepository.save(school);
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
