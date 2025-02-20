package Project.Sprint.Admin.Module;
 
import jakarta.validation.ConstraintViolation;

import jakarta.validation.Validation;

import jakarta.validation.Validator;

import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
 
import Project.Sprint.Admin.Module.Entity.Admin;
 
import java.util.Set;
 
import static org.junit.jupiter.api.Assertions.*;
 
class AdminTest {
 
    private Validator validator;
 
    @BeforeEach

    void setUp() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();

    }
 
    @Test

    void testValidAdmin() {

        Admin admin = new Admin();

        admin.setEmail("admin@example.com");

        admin.setPassword("Password123");

        admin.setName("AdminName");
 
        Set<ConstraintViolation<Admin>> violations = validator.validate(admin);
 
        // Assert that there are no validation errors

        assertTrue(violations.isEmpty(), "There should be no validation errors for a valid Admin");

    }
 
    @Test

    void testInvalidEmail() {

        Admin admin = new Admin();

        admin.setEmail("invalid-email");

        admin.setPassword("Password123");

        admin.setName("AdminName");
 
        Set<ConstraintViolation<Admin>> violations = validator.validate(admin);
 
        // Assert that there is exactly one violation and it is for the email field

        assertEquals(1, violations.size());

        assertTrue(violations.stream()

                .anyMatch(violation -> violation.getMessage().equals("Invalid email format") && violation.getPropertyPath().toString().equals("email")));

    }
    //checking for wrong test case, mismatch regex
    @Test

    void testInvalidEmailFailure() {

        Admin admin = new Admin();

        admin.setEmail("invalid-email");

        admin.setPassword("password123");

        admin.setName("AdminName");
 
        Set<ConstraintViolation<Admin>> violations = validator.validate(admin);
 
        // Assert that there is exactly one violation and it is for the email field

        assertEquals(1, violations.size());

        assertTrue(violations.stream()

                .anyMatch(violation -> violation.getMessage().equals("Invalid email format") && violation.getPropertyPath().toString().equals("email")));

    }
 
 
    @Test

    void testInvalidPasswordFormat() {

        Admin admin = new Admin();

        admin.setEmail("admin@example.com");

        admin.setPassword("password123");

        admin.setName("AdminName");
 
        Set<ConstraintViolation<Admin>> violations = validator.validate(admin);
 
        // Assert that there is exactly one violation for the password format

        assertEquals(1, violations.size());

        assertTrue(violations.stream()

                .anyMatch(violation -> violation.getMessage().equals("Password must contain at least one uppercase letter, one digit, and one alphabet") && violation.getPropertyPath().toString().equals("password")));

    }
 
    @Test

    void testNameTooShort() {

        Admin admin = new Admin();

        admin.setEmail("admin@example.com");

        admin.setPassword("Password123");

        admin.setName("Adm");
 
        Set<ConstraintViolation<Admin>> violations = validator.validate(admin);
 
        // Assert that there is exactly one violation for the name length

        assertEquals(1, violations.size());

        assertTrue(violations.stream()

                .anyMatch(violation -> violation.getMessage().equals("Name must be at least 4 characters long") && violation.getPropertyPath().toString().equals("name")));

    }
    
    
 
 
}

 