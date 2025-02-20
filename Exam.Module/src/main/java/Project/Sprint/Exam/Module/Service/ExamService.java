package Project.Sprint.Exam.Module.Service;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Project.Sprint.Exam.Module.DTO.ExamDTO;
import Project.Sprint.Exam.Module.Entity.Exam;
import Project.Sprint.Exam.Module.Repository.ExamRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public Exam saveExam(ExamDTO examDto) {
    	log.info(examDto+"");
    	Exam exam = mapToExamp(examDto);
        return examRepository.save(exam);
    }

    private Exam mapToExamp(ExamDTO examDto) {
		// TODO Auto-generated method stub
		return Exam.builder()
				.name(examDto.getName())
				.duration(examDto.getDuration())
				.totalMarks(examDto.getTotalMarks())
				.questions(examDto.getQuestions()).build();
	}

	public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Optional<Exam> getExamById(Long id) {
        return examRepository.findById(id);
    }

    public void deleteExamById(Long id) {
        examRepository.deleteById(id);
    }

}
