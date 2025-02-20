package Project.Sprint.Admin.Module.Repository;

import Project.Sprint.Admin.Module.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // You can add custom queries if needed
    // Example: Optional<Admin> findByEmail(String email);
}
