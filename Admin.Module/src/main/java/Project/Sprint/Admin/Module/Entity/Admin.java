package Project.Sprint.Admin.Module.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*\\d).{8,}$",
        message = "Password must contain at least one uppercase letter, one digit, and one alphabet"
    )
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 4, message = "Name must be at least 4 characters long")
    private String name;
}
