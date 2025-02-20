package Project.Sprint.Result.Module.Service;


import Project.Sprint.Result.Module.Entity.*;
import Project.Sprint.Result.Module.Entity.Result;
import Project.Sprint.Result.Module.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    // Save or update a result
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    // Retrieve all results by user ID
    public List<Result> getResultsByUserId(Long userId) {
        return resultRepository.findByUserId(userId);
    }

    // Retrieve all results by exam ID
    public List<Result> getResultsByExamId(Long examId) {
        return resultRepository.findByExamId(examId);
    }

    // Retrieve a single result by ID
    public Optional<Result> getResultById(Long id) {
        return resultRepository.findById(id);
    }

    // Delete a result by ID
    public void deleteResultById(Long id) {
        resultRepository.deleteById(id);
    }

    // Calculate score based on the user's answers
    public int calculateScore(Result result) {
        int score = 0;

        // Fetch the related exam and its questions
        Exam exam = result.getExam();
        if (exam != null) {
            List<Question> questions = exam.getQuestions(); // Assuming Exam entity has a list of Questions

            // Loop through each question and check if the answer is correct
            for (Question question : questions) {
                String userAnswer = result.getUserAnswers().get(question.getId()); // Get the user's answer for this question
                if (userAnswer != null && userAnswer.equals(question.getCorrectAnswer())) {
                    score += question.getMarks(); // Add marks for correct answers
                }
            }
        }

        return score;
    }
}
