package Project.Sprint.Result.Module.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;  // The exam associated with this question

    @Column(nullable = false)
    private String questionText;  // The question text

    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option")
    private List<String> options;  // List of options for the question

    @Column(nullable = false)
    private String correctAnswer;  // The correct answer for the question

    @Column(nullable = false)
    private Integer marks;  // Marks for this question
}
