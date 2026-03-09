package com.fees.management.controller;

import com.fees.management.dto.ApiResponse;
import com.fees.management.dto.FeeSummaryResponse;
import com.fees.management.dto.StudentRequestDto;
import com.fees.management.dto.StudentResponseDto;
import com.fees.management.entity.User;
import com.fees.management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDto createStudent(@Valid @RequestBody StudentRequestDto request,
                                            @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return studentService.createStudent(request, schoolId);
    }

    @GetMapping
    public List<StudentResponseDto> getAllStudents(@AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return studentService.getAllStudents(schoolId);
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id,
                                             @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return studentService.getStudentById(id, schoolId);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteStudent(@PathVariable Long id,
                                     @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        studentService.deleteStudent(id, schoolId);
        return new ApiResponse(200, "Student deleted successfully", id);
    }

    // Fee summary endpoint
    @GetMapping("/{id}/summary")
    public FeeSummaryResponse getFeeSummary(@PathVariable Long id,
                                           @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return studentService.getFeeSummary(id, schoolId);
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id,
                                            @RequestBody StudentRequestDto request,
                                            @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return studentService.updateStudent(id, request, schoolId);
    }

}
