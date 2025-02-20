package Project.Sprint.Admin.Module.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionDTO {


    private String questionText;  // The question text
    private String options;  // Options as a comma-separated string
    private String correctAnswer;  // The correct answer

}

