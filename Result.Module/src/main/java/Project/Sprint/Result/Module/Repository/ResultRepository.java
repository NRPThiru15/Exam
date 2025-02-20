package Project.Sprint.Result.Module.Repository;

import Project.Sprint.Result.Module.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUserId(Long userId);
    List<Result> findByExamId(Long examId);

    // Custom method to delete result by userId and examId
    void deleteByUserIdAndExamId(Long userId, Long examId);
}
