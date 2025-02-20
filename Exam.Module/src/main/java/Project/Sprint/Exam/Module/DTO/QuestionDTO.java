package Project.Sprint.Exam.Module.DTO;

import lombok.Data;
import java.util.List;

@Data
public class QuestionDTO {

    private Long id;
    private String questionText; // The question text
    private List<String> options; // List of options for the question
    private String correctAnswer; // Correct answer
}
