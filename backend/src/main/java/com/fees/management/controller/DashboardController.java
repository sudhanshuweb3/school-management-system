package com.fees.management.controller;

import com.fees.management.dto.DashboardStatsDto;
import com.fees.management.entity.User;
import com.fees.management.repository.CourseRepository;
import com.fees.management.repository.StudentRepository;
import com.fees.management.service.AttendanceService;
import com.fees.management.service.TeacherService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final StudentRepository studentRepository;
    private final TeacherService teacherService;
    private final CourseRepository courseRepository;
    private final AttendanceService attendanceService;

    public DashboardController(StudentRepository studentRepository,
                               TeacherService teacherService,
                               CourseRepository courseRepository,
                               AttendanceService attendanceService) {
        this.studentRepository = studentRepository;
        this.teacherService = teacherService;
        this.courseRepository = courseRepository;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/stats")
    public DashboardStatsDto getStats(@AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();

        long students = studentRepository.countBySchoolId(schoolId);
        long teachers = teacherService.countBySchool(schoolId);
        long courses = courseRepository.countBySchoolId(schoolId);
        long attendanceToday = attendanceService.countTodayAttendance(schoolId);

        return new DashboardStatsDto(students, teachers, courses, attendanceToday);
    }
}
