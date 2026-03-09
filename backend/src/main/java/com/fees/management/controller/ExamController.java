package com.fees.management.controller;

import com.fees.management.entity.Exam;
import com.fees.management.entity.Result;
import com.fees.management.entity.User;
import com.fees.management.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    // Exam endpoints
    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam, @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        Exam created = examService.createExam(exam, schoolId);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getExams(@AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        List<Exam> exams = examService.getExamsBySchool(schoolId);
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Exam>> getExamsByCourse(@PathVariable Long courseId, @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        List<Exam> exams = examService.getExamsByCourse(courseId, schoolId);
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        Exam updated = examService.updateExam(id, exam);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    // Result endpoints
    @PostMapping("/results")
    public ResponseEntity<Result> createResult(@RequestBody Result result, @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        Result created = examService.createResult(result, schoolId);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/results/student/{studentId}")
    public ResponseEntity<List<Result>> getResultsByStudent(@PathVariable Long studentId, @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        List<Result> results = examService.getResultsByStudent(studentId, schoolId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/results/exam/{examId}")
    public ResponseEntity<List<Result>> getResultsByExam(@PathVariable Long examId, @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        List<Result> results = examService.getResultsByExam(examId, schoolId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/results/{id}")
    public ResponseEntity<Result> getResult(@PathVariable Long id) {
        Result result = examService.getResultById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/results/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Long id, @RequestBody Result result) {
        Result updated = examService.updateResult(id, result);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/results/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        examService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
