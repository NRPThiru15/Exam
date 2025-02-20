package Project.Sprint.Exam.Module.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "EXAMS")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;  // Name of the exam

    @Column(nullable = false)
    private Integer duration;  // Duration in minutes

    @Column(nullable = false)
    private Integer totalMarks;  // Total marks of the exam

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;  // List of questions for this exam
}
