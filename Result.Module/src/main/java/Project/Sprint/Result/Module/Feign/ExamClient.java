package Project.Sprint.Result.Module.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import Project.Sprint.Result.Module.DTO.ExamDTO;

@FeignClient(name = "exam-service", url = "http://localhost:8282/api/exams")
public interface ExamClient {
    @GetMapping("/{id}")
    ExamDTO getExamById(@PathVariable Long id);
    
}
