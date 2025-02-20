package Project.Sprint.Admin.Module.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import Project.Sprint.Admin.Module.DTO.ExamDTO;

import java.util.List;

@FeignClient(name = "exam-service", url="http://localhost:8282/api/exams")
public interface ExamClient {

    // Create a new exam
    @PostMapping
    ExamDTO createExam(@RequestBody ExamDTO exam);

    // Get all exams
    @GetMapping
    List<ExamDTO> getAllExams();

    // Get exam by ID
    @GetMapping("/{id}")
    ExamDTO getExamById(@PathVariable Long id);
}
