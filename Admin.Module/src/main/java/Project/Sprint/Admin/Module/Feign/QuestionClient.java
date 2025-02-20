package Project.Sprint.Admin.Module.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import Project.Sprint.Admin.Module.DTO.QuestionDTO;

import java.util.List;

@FeignClient(name = "exam-service")
public interface QuestionClient {

    // Add a question to an exam
    @PostMapping("/api/questions/exam/{examId}")
    QuestionDTO addQuestionToExam(@PathVariable Long examId, @RequestBody QuestionDTO question);

    // Get all questions for an exam
    @GetMapping("/api/questions/exam/{examId}")
    List<QuestionDTO> getQuestionsByExam(@PathVariable Long examId);
}
