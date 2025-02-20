package Project.Sprint.Exam.Module;
 
 
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.Arrays;

import java.util.List;

import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import Project.Sprint.Exam.Module.Controller.ExamController;

import Project.Sprint.Exam.Module.DTO.ExamDTO;

import Project.Sprint.Exam.Module.Entity.Exam;

import Project.Sprint.Exam.Module.Service.ExamService;
 
@ExtendWith(MockitoExtension.class)

class ExamControllerTest {
 
    @Mock

    private ExamService examService;
 
    @InjectMocks

    private ExamController examController;
 
    private MockMvc mockMvc;
 
    private Exam exam;

    private ExamDTO examDTO;
 
    @BeforeEach

    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(examController).build();
 
        // Initialize mock data

        exam = new Exam(1L, "Math Exam", 60, 100, null);  // Mocked Exam entity

    }
 
    @Test

    public void testCreateExam() throws Exception {

        // Arrange: Mock service behavior

        when(examService.saveExam(any(ExamDTO.class))).thenReturn(exam);
 
        // Act & Assert: Perform POST request and verify response

        mockMvc.perform(post("/api/exams")

                        .contentType(APPLICATION_JSON)

                        .content("{\"name\":\"Math Exam\",\"duration\":60,\"totalMarks\":100}"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value("Math Exam"))

                .andExpect(jsonPath("$.duration").value(60))

                .andExpect(jsonPath("$.totalMarks").value(100));

    }
 
    @Test

    public void testGetAllExams() throws Exception {

        // Arrange: Mock service behavior

        List<Exam> examList = Arrays.asList(exam);

        when(examService.getAllExams()).thenReturn(examList);
 
        // Act & Assert: Perform GET request and verify response

        mockMvc.perform(get("/api/exams"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].name").value("Math Exam"))

                .andExpect(jsonPath("$[0].duration").value(60))

                .andExpect(jsonPath("$[0].totalMarks").value(100));

    }
 
    @Test

    public void testGetExamById() throws Exception {

        // Arrange: Mock service behavior

        when(examService.getExamById(1L)).thenReturn(Optional.of(exam));
 
        // Act & Assert: Perform GET request and verify response

        mockMvc.perform(get("/api/exams/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value("Math Exam"))

                .andExpect(jsonPath("$.duration").value(60))

                .andExpect(jsonPath("$.totalMarks").value(100));

    }
 
    @Test

    public void testGetExamByIdNotFound() throws Exception {

        // Arrange: Mock service behavior

        when(examService.getExamById(1L)).thenReturn(Optional.empty());
 
        // Act & Assert: Perform GET request and verify response

        mockMvc.perform(get("/api/exams/1"))

                .andExpect(status().isNotFound());

    }
 
    @Test

    public void testDeleteExam() throws Exception {

        // Arrange: Mock service behavior

        doNothing().when(examService).deleteExamById(1L);
 
        // Act & Assert: Perform DELETE request and verify response

        mockMvc.perform(delete("/api/exams/1"))

                .andExpect(status().isNoContent());
 
        // Verify service method was called

        verify(examService, times(1)).deleteExamById(1L);

    }
    @Test
    public void testGetExamByIdFailure() throws Exception {
        // Arrange: Mock service behavior to return an empty Optional
        when(examService.getExamById(1L)).thenReturn(Optional.empty());

        // Act & Assert: Perform GET request and verify response
        mockMvc.perform(get("/api/exams/1"))
                .andExpect(status().isOk())  // This line will fail because the expected status is 200 OK
                .andExpect(jsonPath("$.name").value("Math Exam"))  // This will fail as the exam is not found
                .andExpect(jsonPath("$.duration").value(60))  // This will fail because the exam is not found
                .andExpect(jsonPath("$.totalMarks").value(100));  // This will fail for the same reason
    }


}

 