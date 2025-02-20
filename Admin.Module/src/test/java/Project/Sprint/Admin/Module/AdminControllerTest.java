package Project.Sprint.Admin.Module;
 
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.when;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.Arrays;

import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import Project.Sprint.Admin.Module.Controller.AdminController;

import Project.Sprint.Admin.Module.DTO.AdminDTO;

import Project.Sprint.Admin.Module.DTO.ExamDTO;

import Project.Sprint.Admin.Module.DTO.QuestionDTO;

import Project.Sprint.Admin.Module.Service.AdminService;
 
@ExtendWith(MockitoExtension.class)

public class AdminControllerTest {
 
    @Mock

    private AdminService adminService;
 
    @InjectMocks

    private AdminController adminController;
 
    private MockMvc mockMvc;
 
    private AdminDTO adminDTO;

    private ExamDTO examDTO;

    private QuestionDTO questionDTO;
 
    @BeforeEach

    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
 
        // Initialize mock data

        examDTO = new ExamDTO("Math Exam", 60, 100, null); // Assuming null for simplicity

    }
 
    @Test

    public void testCreateExam() throws Exception {

        // Arrange: Mock service behavior

        when(adminService.createExam(any(ExamDTO.class))).thenReturn(examDTO);
 
        // Act & Assert: Perform POST request and verify response

        mockMvc.perform(post("/api/admin/exams")

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

        List<ExamDTO> examList = Arrays.asList(examDTO);

        when(adminService.getAllExams()).thenReturn(examList);
 
        // Act & Assert: Perform GET request and verify response

        mockMvc.perform(get("/api/admin/exams"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].name").value("Math Exam"))

                .andExpect(jsonPath("$[0].duration").value(60))

                .andExpect(jsonPath("$[0].totalMarks").value(100));

    }
    
    @Test
    public void testCreateExamFailure() throws Exception {
        // Arrange: Mock service behavior to return null
        when(adminService.createExam(any(ExamDTO.class))).thenReturn(null);

        // Act & Assert: Perform POST request and verify response
        mockMvc.perform(post("/api/admin/exams")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\":\"Math Exam\",\"duration\":60,\"totalMarks\":100}"))
                .andExpect(status().isInternalServerError())  // Expecting server error due to null response
                .andExpect(jsonPath("$.name").doesNotExist())  // 'name' field shouldn't exist
                .andExpect(jsonPath("$.duration").doesNotExist())  // 'duration' field shouldn't exist
                .andExpect(jsonPath("$.totalMarks").doesNotExist());  // 'totalMarks' field shouldn't exist
    }

 
 
}

 