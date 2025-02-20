package Project.Sprint.Result.Module;
 
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
 
import com.google.common.base.Optional;
 
import Project.Sprint.Result.Module.Controller.ResultController;

import Project.Sprint.Result.Module.Entity.Result;

import Project.Sprint.Result.Module.Service.ResultService;

import antlr.collections.List;
 
@WebMvcTest(ResultController.class)  // Testing the ResultController class

public class ResultControllerTest {
 
    @Mock

    private ResultService resultService;  // Mocking the ResultService
 
    @InjectMocks

    private ResultController resultController;  // Injecting mocks into the controller
 
    private MockMvc mockMvc;
 
    private Result result;
 
    @BeforeEach

    public void setUp() {

        // Initialize the MockMvc object before each test

        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(resultController).build();
 
        // Creating a sample result object to return from the service

        result = new Result();

        result.setMarksObtained(80);

    }
 
    @Test

    public void testSaveResult() throws Exception {

        // Arrange: Mock service behavior

        when(resultService.saveResult(any(Result.class))).thenReturn(result);
 
        // Act & Assert: Perform POST request and verify response

        mockMvc.perform(post("/api/results")

                        .contentType("application/json")

                        .content("{\"marksObtained\":80}"))  // Ensure marksObtained is provided

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.marksObtained").value(80));  // Assert the marksObtained field

    }
    
    
 
   
 
}

 