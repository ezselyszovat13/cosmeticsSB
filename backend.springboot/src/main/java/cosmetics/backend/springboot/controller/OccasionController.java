package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.service.OccasionServiceImpl;
import cosmetics.backend.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class OccasionController {
    private OccasionServiceImpl occasionService;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public OccasionController(OccasionServiceImpl occasionService, UserServiceImpl userServiceImpl) {
        this.occasionService = occasionService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("occasions")
    public List<Occasion> getOccasions() { return this.occasionService.getOccasions(); }

    @PostMapping("occasions/{userId}")
    public Occasion addOccasion(@PathVariable Long userId){
        return this.occasionService.addOccasion(userId);
    }
}
