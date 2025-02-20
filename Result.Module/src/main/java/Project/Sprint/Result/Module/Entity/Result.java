package Project.Sprint.Result.Module.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

import Project.Sprint.Result.Module.DTO.ExamDTO;

@Data
@Entity
@Table(name = "results") // Maps to the "results" table in the database
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generates unique IDs
    private Long id;

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "User ID cannot be null")
    private Long userId; // Foreign key referencing the "users" table

    @Column(name = "exam_id", nullable = false)
    @NotNull(message = "Exam ID cannot be null")
    private Long examId; // Foreign key referencing the "exams" table

    @Column(name = "marks_obtained", nullable = false)
    @NotNull(message = "Marks obtained cannot be null")
    @Min(value = 0, message = "Marks obtained must be at least 0")
    private Integer marksObtained; // Marks obtained by the user

    @ElementCollection
    @MapKeyColumn(name = "question_id")
    @Column(name = "user_answer")
    private Map<Long, String> userAnswers;  // Store user answers as a map of question IDs to answers
    
    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Exam exam; // The related exam
}
