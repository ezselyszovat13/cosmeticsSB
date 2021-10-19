package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.model.Status;
import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.repository.OccasionRepository;
import cosmetics.backend.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class OccasionController {

    @Autowired
    private OccasionRepository occasionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("occasions")
    public List<Occasion> getOccasions() { return this.occasionRepository.findAll(); }

    @PostMapping("occasions/{userId}")
    public Occasion addOccasion(@PathVariable Long userId){
        Occasion occ = new Occasion();
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new RuntimeException("Could not found occasion with the given ID"));
        occ.setUser(user);
        occ.setStatus(Status.DONE);
        occ.setTimestamp(LocalDateTime.now());
        return occasionRepository.save(occ);
    }
}
