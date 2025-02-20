package Project.Sprint.Result.Module.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import Project.Sprint.Result.Module.DTO.UserDTO;

@FeignClient(name = "user-service", url = "http://localhost:8181/api/users")
public interface UserClient {
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
