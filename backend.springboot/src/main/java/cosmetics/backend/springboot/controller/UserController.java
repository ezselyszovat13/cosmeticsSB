package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.repository.UserRepository;
import cosmetics.backend.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<User> getUsers() { return this.userService.getUsers(); }

    @PostMapping("registeruser")
    public User registerUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        if(tempEmail != null && !tempEmail.isEmpty()){
            User userObj = userService.fetchUserByEmail(tempEmail);
            if(userObj != null){
                throw new Exception("A megadott e-mail cím már használatban van!");
            }
        }
        else{
            throw new Exception("Az e-mail cím nem megfelelő!");
        }
        return this.userService.saveUser(user);
    }

    @PostMapping("loginuser")
    public User loginUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        String tempPass = user.getPassword();
        if(tempEmail != null && tempPass != null){
            User userObj = userService.fetchUserByEmailAndPassword(tempEmail,tempPass);
            if(userObj == null){
                throw new Exception("A megadott adatokkal felhasználó nem található!");
            }
            return userObj;
        }
        else{
            throw new Exception("Az adatok nem megfelelően kerültek megadásra!");
        }
    }
}
