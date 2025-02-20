package Project.Sprint.Exam.Module;
 
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
 
import java.util.Arrays;

import java.util.List;

import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
 
import Project.Sprint.Exam.Module.DTO.ExamDTO;

import Project.Sprint.Exam.Module.Entity.Exam;

import Project.Sprint.Exam.Module.Repository.ExamRepository;

import Project.Sprint.Exam.Module.Service.ExamService;
 
@ExtendWith(MockitoExtension.class)  // JUnit 5 with Mockito

public class ExamServiceTest {
 
    @Mock

    private ExamRepository examRepository;  // Mock the ExamRepository
 
    @InjectMocks

    private ExamService examService;  // Inject the mocked repository into the service
 
    private ExamDTO examDto;

    private Exam exam;
 
    @BeforeEach

    public void setUp() {

        // Prepare a sample ExamDTO and Exam entity for testing

        exam = new Exam(1L, "Math Exam", 60, 100, null);  // Sample exam entity

    }
 
 
    @Test

    public void testGetAllExams() {

        // Arrange: mock the repository's findAll method

        when(examRepository.findAll()).thenReturn(Arrays.asList(exam));
 
        // Act: Call the getAllExams method

        List<Exam> exams = examService.getAllExams();
 
        // Assert: Verify that the repository's findAll method was called and that the result is correct

        verify(examRepository, times(1)).findAll();  // Ensure findAll method was called once

        assertNotNull(exams);

        assertEquals(1, exams.size());

        assertEquals("Math Exam", exams.get(0).getName());

    }
 
    @Test

    public void testGetExamById() {

        // Arrange: mock the repository's findById method

        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
 
        // Act: Call the getExamById method

        Optional<Exam> foundExam = examService.getExamById(1L);
 
        // Assert: Verify that the repository's findById method was called and the result is correct

        verify(examRepository, times(1)).findById(1L);  // Ensure findById method was called once

        assertTrue(foundExam.isPresent());

        assertEquals("Math Exam", foundExam.get().getName());

    }
    @Test
    public void testGetExamByIdFailure() {
        // Arrange: mock the repository's findById method to return an empty Optional
        when(examRepository.findById(1L)).thenReturn(Optional.empty());

        // Act: Call the getExamById method
        Optional<Exam> foundExam = examService.getExamById(1L);

        // Assert: Verify that the repository's findById method was called and that the result is empty
        verify(examRepository, times(1)).findById(1L);  // Ensure findById method was called once
        
        // Assert that the exam is not found
        assertTrue(foundExam.isEmpty());  // This should fail because the test expects a present exam
        
        // This is the failure point: the test expects an exam to be present, but it is empty
    }


}

 