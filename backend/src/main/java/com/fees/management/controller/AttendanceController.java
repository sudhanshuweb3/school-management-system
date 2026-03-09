package com.fees.management.controller;

import com.fees.management.dto.AttendanceRequestDto;
import com.fees.management.dto.AttendanceResponseDto;
import com.fees.management.entity.User;
import com.fees.management.service.AttendanceService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public AttendanceResponseDto markAttendance(@RequestBody AttendanceRequestDto request,
                                                @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return attendanceService.markAttendance(request, schoolId);
    }

    @GetMapping
    public List<AttendanceResponseDto> getAllAttendance(@AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return attendanceService.getAllAttendance(schoolId);
    }

    @GetMapping("/student/{studentId}")
    public List<AttendanceResponseDto> getByStudent(@PathVariable Long studentId,
                                                     @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return attendanceService.getAttendanceByStudent(studentId, schoolId);
    }

    @GetMapping("/date/{date}")
    public List<AttendanceResponseDto> getByDate(@PathVariable String date,
                                                  @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return attendanceService.getAttendanceByDate(date, schoolId);
    }
}
