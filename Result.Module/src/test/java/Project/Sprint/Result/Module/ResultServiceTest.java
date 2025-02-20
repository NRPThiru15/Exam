package Project.Sprint.Result.Module;
 
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
 
import java.util.Arrays;

import java.util.List;

import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
 
import Project.Sprint.Result.Module.Entity.Result;

import Project.Sprint.Result.Module.Repository.ResultRepository;

import Project.Sprint.Result.Module.Service.ResultService;
 
@ExtendWith(MockitoExtension.class)  // JUnit 5 with Mockito

public class ResultServiceTest {
 
    @Mock

    private ResultRepository resultRepository;  // Mock the ResultRepository
 
    @InjectMocks

    private ResultService resultService;  // Inject the mocked ResultRepository into ResultService
 
    private Result result;
 
    @BeforeEach

    public void setUp() {

        // Set up the mock data for each test case

        result = new Result();

        result.setId(1L);

        result.setUserId(101L);

        result.setExamId(202L);

    }
 
    @Test

    public void testSaveResult() {

        // Arrange: Mock the behavior of resultRepository.save()

        when(resultRepository.save(any(Result.class))).thenReturn(result);
 
        // Act: Call the saveResult method

        Result savedResult = resultService.saveResult(result);
 
        // Assert: Verify that the resultRepository.save() method was called and the result is correct

        verify(resultRepository, times(1)).save(any(Result.class));  // Ensure save was called once

        assertNotNull(savedResult);

        assertEquals(result.getId(), savedResult.getId());

        assertEquals(result.getUserId(), savedResult.getUserId());

        assertEquals(result.getExamId(), savedResult.getExamId());

    }
 
    @Test

    public void testGetResultsByUserId() {

        // Arrange: Mock the behavior of resultRepository.findByUserId()

        when(resultRepository.findByUserId(101L)).thenReturn(Arrays.asList(result));
 
        // Act: Call the getResultsByUserId method

        List<Result> results = resultService.getResultsByUserId(101L);
 
        // Assert: Verify that the resultRepository.findByUserId() method was called and the result is correct

        verify(resultRepository, times(1)).findByUserId(101L);  // Ensure findByUserId was called once

        assertNotNull(results);

        assertEquals(1, results.size());

        assertEquals(result.getUserId(), results.get(0).getUserId());

    }
 
    @Test

    public void testGetResultsByExamId() {

        // Arrange: Mock the behavior of resultRepository.findByExamId()

        when(resultRepository.findByExamId(202L)).thenReturn(Arrays.asList(result));
 
        // Act: Call the getResultsByExamId method

        List<Result> results = resultService.getResultsByExamId(202L);
 
        // Assert: Verify that the resultRepository.findByExamId() method was called and the result is correct

        verify(resultRepository, times(1)).findByExamId(202L);  // Ensure findByExamId was called once

        assertNotNull(results);

        assertEquals(1, results.size());

        assertEquals(result.getExamId(), results.get(0).getExamId());

    }
 
    @Test

    public void testGetResultById() {

        // Arrange: Mock the behavior of resultRepository.findById()

        when(resultRepository.findById(1L)).thenReturn(Optional.of(result));
 
        // Act: Call the getResultById method

        Optional<Result> foundResult = resultService.getResultById(1L);
 
        // Assert: Verify that the resultRepository.findById() method was called and the result is correct

        verify(resultRepository, times(1)).findById(1L);  // Ensure findById was called once

        assertTrue(foundResult.isPresent());

        assertEquals(result.getId(), foundResult.get().getId());

        assertEquals(result.getUserId(), foundResult.get().getUserId());

    }
 
    @Test

    public void testDeleteResultById() {

        // Arrange: No need to mock, we will just verify delete method is called
 
        // Act: Call the deleteResultById method

        resultService.deleteResultById(1L);
 
        // Assert: Verify that the resultRepository.deleteById() method was called

        verify(resultRepository, times(1)).deleteById(1L);  // Ensure deleteById was called once

    }
    
    
    @Test
    public void testGetResultsByUserIdFailure() {
        // Arrange: Mock the behavior of resultRepository.findByUserId() to return an empty list
        when(resultRepository.findByUserId(101L)).thenReturn(Arrays.asList());  // No results returned

        // Act: Call the getResultsByUserId method
        List<Result> results = resultService.getResultsByUserId(101L);

        // Assert: Verify that the resultRepository.findByUserId() method was called and the result is incorrect
        verify(resultRepository, times(1)).findByUserId(101L);  // Ensure findByUserId was called once

        assertNotNull(results);  // This will pass
        assertEquals(1, results.size());  // This will fail because the mock returns an empty list, but we're asserting that the list should contain 1 result
    }


}

 