package Project.Sprint.Admin.Module.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Question {


    private String questionText;  // The question text
    private String options;  // Options as a comma-separated string
    private String correctAnswer;  // The correct answer

 
}
