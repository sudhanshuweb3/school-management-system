package com.fees.management.service;

import com.fees.management.entity.Exam;
import com.fees.management.entity.Result;
import com.fees.management.entity.School;
import com.fees.management.repository.ExamRepository;
import com.fees.management.repository.ResultRepository;
import com.fees.management.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final ResultRepository resultRepository;
    private final SchoolRepository schoolRepository;

    public ExamService(ExamRepository examRepository, ResultRepository resultRepository, SchoolRepository schoolRepository) {
        this.examRepository = examRepository;
        this.resultRepository = resultRepository;
        this.schoolRepository = schoolRepository;
    }

    // Exam CRUD
    public Exam createExam(Exam exam, Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found"));
        exam.setSchool(school);
        return examRepository.save(exam);
    }

    public List<Exam> getExamsBySchool(Long schoolId) {
        return examRepository.findBySchoolId(schoolId);
    }

    public List<Exam> getExamsByCourse(Long courseId, Long schoolId) {
        return examRepository.findByCourseIdAndSchoolId(courseId, schoolId);
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }

    public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = getExamById(id);
        exam.setName(examDetails.getName());
        exam.setExamDate(examDetails.getExamDate());
        exam.setSubject(examDetails.getSubject());
        exam.setCourse(examDetails.getCourse());
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }

    // Result CRUD
    public Result createResult(Result result, Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found"));
        result.setSchool(school);
        return resultRepository.save(result);
    }

    public List<Result> getResultsByStudent(Long studentId, Long schoolId) {
        return resultRepository.findByStudentIdAndSchoolId(studentId, schoolId);
    }

    public List<Result> getResultsByExam(Long examId, Long schoolId) {
        return resultRepository.findByExamIdAndSchoolId(examId, schoolId);
    }

    public Result getResultById(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));
    }

    public Result updateResult(Long id, Result resultDetails) {
        Result result = getResultById(id);
        result.setMarksObtained(resultDetails.getMarksObtained());
        result.setGrade(resultDetails.getGrade());
        return resultRepository.save(result);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}
