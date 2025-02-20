package Project.Sprint.Result.Module.Controller;

import Project.Sprint.Result.Module.Entity.Result;
import Project.Sprint.Result.Module.Service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // Create or update a result
    @PostMapping
    public ResponseEntity<Result> saveResult(@Valid @RequestBody Result result) {
        // Calculate score before saving the result
        int score = resultService.calculateScore(result);
        result.setMarksObtained(score);

        Result savedResult = resultService.saveResult(result);
        return ResponseEntity.ok(savedResult);
    }

    // Retrieve results by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Result>> getResultsByUserId(@PathVariable Long userId) {
        List<Result> results = resultService.getResultsByUserId(userId);
        return ResponseEntity.ok(results);
    }

    // Retrieve results by exam ID
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<Result>> getResultsByExamId(@PathVariable Long examId) {
        List<Result> results = resultService.getResultsByExamId(examId);
        return ResponseEntity.ok(results);
    }

    // Retrieve a single result by ID
    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Long id) {
        return resultService.getResultById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a result by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultById(@PathVariable Long id) {
        resultService.deleteResultById(id);
        return ResponseEntity.noContent().build();
    }
}
