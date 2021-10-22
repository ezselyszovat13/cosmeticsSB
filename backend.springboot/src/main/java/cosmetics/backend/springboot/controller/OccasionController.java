package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.model.Status;
import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.repository.OccasionRepository;
import cosmetics.backend.springboot.repository.UserRepository;
import cosmetics.backend.springboot.service.OccasionService;
import cosmetics.backend.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class OccasionController {
    private OccasionService occasionService;
    private UserService userService;

    @Autowired
    public OccasionController(OccasionService occasionService, UserService userService) {
        this.occasionService = occasionService;
        this.userService = userService;
    }

    @GetMapping("occasions")
    public List<Occasion> getOccasions() { return this.occasionService.getOccasions(); }

    @PostMapping("occasions/{userId}")
    public Occasion addOccasion(@PathVariable Long userId){
        return this.occasionService.addOccasion(userId);
    }
}
