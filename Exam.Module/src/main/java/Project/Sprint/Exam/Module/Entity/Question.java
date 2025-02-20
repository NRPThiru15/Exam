package Project.Sprint.Exam.Module.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "QUESTIONS")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String questionText;  // The question text

    @Column(nullable = false)
    private String options;  // Options as a comma-separated string

    @Column(nullable = false)
    private String correctAnswer;  // The correct answer

//    @ManyToOne
//    @JoinColumn(name = "exam_id", nullable = false)
//    private Exam exam;  // Linked exam
}
