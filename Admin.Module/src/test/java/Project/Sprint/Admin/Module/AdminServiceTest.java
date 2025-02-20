package Project.Sprint.Admin.Module;
 
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
 
import java.util.Arrays;

import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
 
import Project.Sprint.Admin.Module.DTO.AdminDTO;

import Project.Sprint.Admin.Module.DTO.ExamDTO;

import Project.Sprint.Admin.Module.DTO.QuestionDTO;

import Project.Sprint.Admin.Module.Entity.Admin;

import Project.Sprint.Admin.Module.Feign.ExamClient;

import Project.Sprint.Admin.Module.Feign.QuestionClient;

import Project.Sprint.Admin.Module.Repository.AdminRepository;

import Project.Sprint.Admin.Module.Service.AdminService;
 
@ExtendWith(MockitoExtension.class)  // JUnit 5 with Mockito

public class AdminServiceTest {
 
    @Mock

    private ExamClient examClient;  // Mock the ExamClient
 
    @Mock

    private QuestionClient questionClient;  // Mock the QuestionClient
 
    @Mock

    private AdminRepository adminRepository;  // Mock the AdminRepository
 
    @InjectMocks

    private AdminService adminService;  // Inject the mocked dependencies into AdminService
 
    private AdminDTO adminDTO;

    private ExamDTO examDTO;

    private QuestionDTO questionDTO;
 
    @BeforeEach

    public void setUp() {

        // Set up the mock data for each test case
 
        adminDTO = new AdminDTO();

        adminDTO.setEmail("admin@example.com");

        adminDTO.setName("Admin");

        adminDTO.setPassword("Password123");
 
        examDTO = new ExamDTO("Math Exam", 60, 100, null);
 
    }
 
    @Test

    public void testCreateAdmin() {

        // Arrange: Mock the behavior of adminRepository.save()

        Admin admin = new Admin();

        admin.setEmail(adminDTO.getEmail());

        admin.setName(adminDTO.getName());

        admin.setPassword(adminDTO.getPassword());
 
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);
 
        // Act: Call the createAdmin method

        AdminDTO createdAdminDTO = adminService.createAdmin(adminDTO);
 
        // Assert: Verify that the adminRepository.save() method was called and the result is correct

        verify(adminRepository, times(1)).save(any(Admin.class));  // Ensure save was called once

        assertNotNull(createdAdminDTO);

        assertEquals(adminDTO.getEmail(), createdAdminDTO.getEmail());

        assertEquals(adminDTO.getName(), createdAdminDTO.getName());

    }
 
    @Test

    public void testCreateExam() {

        // Arrange: Mock the behavior of examClient.createExam()

        when(examClient.createExam(any(ExamDTO.class))).thenReturn(examDTO);
 
        // Act: Call the createExam method

        ExamDTO createdExamDTO = adminService.createExam(examDTO);
 
        // Assert: Verify that the examClient.createExam() method was called and the result is correct

        verify(examClient, times(1)).createExam(any(ExamDTO.class));  // Ensure createExam was called once

        assertNotNull(createdExamDTO);

        assertEquals(examDTO.getName(), createdExamDTO.getName());

    }
 
    @Test

    public void testGetAllExams() {

        // Arrange: Mock the behavior of examClient.getAllExams()

        when(examClient.getAllExams()).thenReturn(Arrays.asList(examDTO));
 
        // Act: Call the getAllExams method

        List<ExamDTO> exams = adminService.getAllExams();
 
        // Assert: Verify that the examClient.getAllExams() method was called and the result is correct

        verify(examClient, times(1)).getAllExams();  // Ensure getAllExams was called once

        assertNotNull(exams);

        assertEquals(1, exams.size());

        assertEquals(examDTO.getName(), exams.get(0).getName());

    }
    @Test
    public void testCreateExamFailure() {

        // Arrange: Mock the behavior of examClient.createExam() to return null
        when(examClient.createExam(any(ExamDTO.class))).thenReturn(null);
        
        // Act: Call the createExam method
        ExamDTO createdExamDTO = adminService.createExam(examDTO);
        
        // Assert: Verify that the examClient.createExam() method was called
        verify(examClient, times(1)).createExam(any(ExamDTO.class));  // Ensure createExam was called once
        
        // Assert that the createdExamDTO is null, which should cause the test to fail
        assertNotNull(createdExamDTO);  // This assertion will fail because createdExamDTO will be null
    }
 
}

 