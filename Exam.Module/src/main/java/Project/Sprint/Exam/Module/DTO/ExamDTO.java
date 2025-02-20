package Project.Sprint.Exam.Module.DTO;

import java.util.List;

import Project.Sprint.Exam.Module.Entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExamDTO {

    private Long id;
    private String name; // Name of the exam
    private Integer duration; // Duration of the exam in minutes
    private Integer totalMarks; // Total marks for the exam
    private List<Question> questions;
}
