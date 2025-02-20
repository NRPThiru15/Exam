package Project.Sprint.Result.Module.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Question> questions; // List of questions for this exam
}
