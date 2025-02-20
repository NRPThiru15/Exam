package Project.Sprint.Result.Module.DTO;

import java.util.List;

import Project.Sprint.Result.Module.Entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExamDTO {
    private Long id;
    private String name;
    private Integer duration;
    private Integer totalMarks;
	
	
}
