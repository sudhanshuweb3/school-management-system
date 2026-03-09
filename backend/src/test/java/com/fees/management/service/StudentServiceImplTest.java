package com.fees.management.service;

import com.fees.management.dto.StudentRequestDto;
import com.fees.management.entity.Course;
import com.fees.management.entity.Student;
import com.fees.management.repository.CourseRepository;
import com.fees.management.repository.PaymentRepository;
import com.fees.management.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateStudentSuccessfully() {
        // Arrange
        StudentRequestDto request = new StudentRequestDto();
        request.setName("Rahul");
        request.setEmail("rahul@gmail.com");
        request.setPhone("9999999999");
        request.setCourseId(1L);

        Course course = new Course();
        course.setId(1L);
        course.setName("Java");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(studentRepository.save(any(Student.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        var response = studentService.createStudent(request, 1L);

        // Assert
        assertEquals("Rahul", response.getName());
        assertEquals("Java", response.getCourseName());
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowIfCourseNotFound() {
        StudentRequestDto request = new StudentRequestDto();
        request.setCourseId(99L);

        when(courseRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            studentService.createStudent(request, 1L);
        });
    }
}
