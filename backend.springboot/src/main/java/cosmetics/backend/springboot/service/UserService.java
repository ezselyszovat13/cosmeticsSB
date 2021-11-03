package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers() { return userRepository.findAll(); }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User fetchUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User fetchUserByEmailAndPassword(String email, String pw){
        return userRepository.findByEmailAndPassword(email, pw);
    }
}
