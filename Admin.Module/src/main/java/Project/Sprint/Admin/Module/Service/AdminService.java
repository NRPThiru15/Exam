package Project.Sprint.Admin.Module.Service;

import Project.Sprint.Admin.Module.DTO.AdminDTO;
import Project.Sprint.Admin.Module.DTO.ExamDTO;
import Project.Sprint.Admin.Module.DTO.QuestionDTO;
import Project.Sprint.Admin.Module.Entity.Admin;
import Project.Sprint.Admin.Module.Feign.ExamClient;
import Project.Sprint.Admin.Module.Feign.QuestionClient;
import Project.Sprint.Admin.Module.Repository.AdminRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminService {

    @Autowired
    private ExamClient examClient;

    @Autowired
    private QuestionClient questionClient;
    
    @Autowired
    private AdminRepository adminRepository;
 // Create a new admin
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setEmail(adminDTO.getEmail());
        admin.setName(adminDTO.getName());
        admin.setPassword(adminDTO.getPassword());

        Admin savedAdmin = adminRepository.save(admin);

        // Convert savedAdmin to AdminDTO
        AdminDTO savedAdminDTO = new AdminDTO();
        savedAdminDTO.setId(savedAdmin.getId());
        savedAdminDTO.setEmail(savedAdmin.getEmail());
        savedAdminDTO.setName(savedAdmin.getName());
        savedAdminDTO.setPassword(savedAdmin.getPassword());  // Be cautious about returning password in response

        return savedAdminDTO;
    }

//    // Admin: Create an exam
//    public ExamDTO createExam(ExamDTO examDTO) {
//    	
//    	log.info(examDTO+"");
//        return examClient.createExam(examDTO);
//    }
    public ExamDTO createExam(ExamDTO examDTO) {
        try {
            return examClient.createExam(examDTO);
        } catch (FeignException e) {
            log.error("Failed to create exam: {}", e.getMessage());
            throw new RuntimeException("Error creating exam");
        }
    }

    // Admin: Fetch all exams
    public List<ExamDTO> getAllExams() {
        return examClient.getAllExams();
    }

    // Admin: Add a question to an exam
    public QuestionDTO addQuestionToExam(Long examId, QuestionDTO questionDTO) {
        return questionClient.addQuestionToExam(examId, questionDTO);
    }

    // Admin: Get questions for a specific exam
    public List<QuestionDTO> getQuestionsByExam(Long examId) {
        return questionClient.getQuestionsByExam(examId);
    }
}
