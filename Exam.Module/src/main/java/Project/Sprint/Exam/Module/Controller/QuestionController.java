package Project.Sprint.Exam.Module.Controller;

import Project.Sprint.Exam.Module.Entity.Question;
import Project.Sprint.Exam.Module.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/exam/{examId}")
    public ResponseEntity<Question> addQuestionToExam(@PathVariable Long examId, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestionToExam(examId, question));
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<Question>> getQuestionsByExam(@PathVariable Long examId) {
        return ResponseEntity.ok(questionService.getQuestionsByExam(examId));
    }
}
