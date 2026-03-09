package com.fees.management.controller;

import com.fees.management.dto.ApiResponse;
import com.fees.management.dto.TeacherRequestDto;
import com.fees.management.dto.TeacherResponseDto;
import com.fees.management.entity.User;
import com.fees.management.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public TeacherResponseDto createTeacher(@Valid @RequestBody TeacherRequestDto request,
                                            @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return teacherService.createTeacher(request, schoolId);
    }

    @GetMapping
    public List<TeacherResponseDto> getAllTeachers(@AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return teacherService.getAllTeachers(schoolId);
    }

    @GetMapping("/{id}")
    public TeacherResponseDto getTeacher(@PathVariable Long id,
                                         @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return teacherService.getTeacherById(id, schoolId);
    }

    @PutMapping("/{id}")
    public TeacherResponseDto updateTeacher(@PathVariable Long id,
                                            @Valid @RequestBody TeacherRequestDto request,
                                            @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return teacherService.updateTeacher(id, request, schoolId);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteTeacher(@PathVariable Long id,
                                     @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        teacherService.deleteTeacher(id, schoolId);
        return new ApiResponse(200, "Teacher deleted successfully", id);
    }
}
