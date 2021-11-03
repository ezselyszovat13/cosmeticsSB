package cosmetics.backend.springboot.dto;

import lombok.Data;

@Data
public class UserRoleRequest {
    private String email;
    private String roleName;
}
