package Project.Sprint.Admin.Module.DTO;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExamDTO {

  
    private String name; // Name of the exam
    private Integer duration; // Duration of the exam in minutes
    private Integer totalMarks; // Total marks for the exam
    private List<Question> questions;
}
