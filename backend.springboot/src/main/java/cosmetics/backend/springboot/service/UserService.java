package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Role;
import cosmetics.backend.springboot.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUser(String email);
    User getUserById(Long id);
    List<User> getUsers();
    List<Role> getRoles();
    User findUserByEmailAndPassword(String email, String password);
}
