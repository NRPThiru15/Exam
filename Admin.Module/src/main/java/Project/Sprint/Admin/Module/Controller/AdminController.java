package Project.Sprint.Admin.Module.Controller;

import Project.Sprint.Admin.Module.DTO.ExamDTO;
import Project.Sprint.Admin.Module.DTO.QuestionDTO;
import Project.Sprint.Admin.Module.DTO.AdminDTO;
import Project.Sprint.Admin.Module.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoint to create a new admin
    @PostMapping("/create")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        return ResponseEntity.ok(adminService.createAdmin(adminDTO));
    }

    // Endpoint to create an exam
    @PostMapping("/exams")
    public ResponseEntity<ExamDTO> createExam(@RequestBody ExamDTO examDTO) {
        return ResponseEntity.ok(adminService.createExam(examDTO));
    }

    // Endpoint to fetch all exams
    @GetMapping("/exams")
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        return ResponseEntity.ok(adminService.getAllExams());
    }

    // Endpoint to add a question to an exam
    @PostMapping("/exams/{examId}/questions")
    public ResponseEntity<QuestionDTO> addQuestionToExam(
            @PathVariable Long examId,
            @RequestBody QuestionDTO questionDTO
    ) {
        return ResponseEntity.ok(adminService.addQuestionToExam(examId, questionDTO));
    }

    // Endpoint to get questions for a specific exam
    @GetMapping("/exams/{examId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByExam(@PathVariable Long examId) {
        return ResponseEntity.ok(adminService.getQuestionsByExam(examId));
    }
}
