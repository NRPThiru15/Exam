package Project.Sprint.Exam.Module.Service;

import Project.Sprint.Exam.Module.Entity.Question;
import Project.Sprint.Exam.Module.Entity.Exam;
import Project.Sprint.Exam.Module.Repository.QuestionRepository;
import Project.Sprint.Exam.Module.Repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    public Question addQuestionToExam(Long examId, Question question) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

//        question.setExam(exam);
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByExam(Long examId) {
        return questionRepository.findAll();  // Customize if needed for filtering by examId
    }
}
