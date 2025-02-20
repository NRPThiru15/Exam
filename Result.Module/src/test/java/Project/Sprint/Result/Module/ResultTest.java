package Project.Sprint.Result.Module;
 
import Project.Sprint.Result.Module.Entity.Result;

import Project.Sprint.Result.Module.Repository.ResultRepository;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;
 
import java.util.Arrays;

import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;
 
@SpringBootTest

class ResultRepositoryTest {
 
    @Mock

    private ResultRepository resultRepository;  // Mock the ResultRepository
 
    private Result result;
 
    @BeforeEach

    void setUp() {

        // Set up a sample result

        result = new Result();

        result.setUserId(1L);

        result.setExamId(101L);

        result.setMarksObtained(85);

    }
 
    @Test

    void testSaveResult() {

        // Mock the save method to return the same result that is passed in

        Mockito.when(resultRepository.save(any(Result.class))).thenReturn(result);
 
        // Call the repository's save method

        Result savedResult = resultRepository.save(result);
 
        // Verify that the returned result is not null and has the expected values

        assertNotNull(savedResult);

        assertEquals(1L, savedResult.getUserId());

        assertEquals(101L, savedResult.getExamId());

        assertEquals(85, savedResult.getMarksObtained());

    }
 
    @Test

    void testFindResultById() {

        // Mock the findById method to return an Optional containing the result

        Mockito.when(resultRepository.findById(anyLong())).thenReturn(Optional.of(result));
 
        // Call the repository's findById method

        Optional<Result> foundResult = resultRepository.findById(1L);
 
        // Verify that the result is present and has the expected values

        assertTrue(foundResult.isPresent());

        assertEquals(1L, foundResult.get().getUserId());

        assertEquals(101L, foundResult.get().getExamId());

        assertEquals(85, foundResult.get().getMarksObtained());

    }
 
    @Test

    void testFindResultByIdNotFound() {

        // Mock the findById method to return an empty Optional when the result is not found

        Mockito.when(resultRepository.findById(anyLong())).thenReturn(Optional.empty());
 
        // Call the repository's findById method for a non-existing result

        Optional<Result> foundResult = resultRepository.findById(1L);
 
        // Assert that the result is not found

        assertFalse(foundResult.isPresent());

    }
 
    @Test

    void testFindAllResults() {

        // Mock the findAll method to return a list of results

        Result result2 = new Result();

        result2.setUserId(2L);

        result2.setExamId(102L);

        result2.setMarksObtained(90);
 
        Mockito.when(resultRepository.findAll()).thenReturn(Arrays.asList(result, result2));
 
        // Call the repository's findAll method

        var results = resultRepository.findAll();
 
        // Verify that the list is not empty and contains the correct number of results

        assertNotNull(results);

        assertEquals(2, results.size());

        assertEquals(1L, results.get(0).getUserId());

        assertEquals(2L, results.get(1).getUserId());

    }
 
    @Test

    void testDeleteResult() {

        // Mock the delete method (it's a void method, so we verify the interaction)

        resultRepository.delete(result);
 
        // Verify that the delete method was called once

        Mockito.verify(resultRepository, Mockito.times(1)).delete(result);

    }
    
    @Test
    void testSaveResultFailure() {

        // Arrange: Mock the save method to return null (simulate a failure in saving)
        Mockito.when(resultRepository.save(any(Result.class))).thenReturn(null);

        // Act: Call the repository's save method
        Result savedResult = resultRepository.save(result);

        // Assert: Verify that the result is null, causing the test to fail
        assertNotNull(savedResult);  // This assertion will fail because savedResult is null
    }


}

 