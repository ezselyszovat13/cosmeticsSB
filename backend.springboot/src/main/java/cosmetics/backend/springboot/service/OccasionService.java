package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.model.Status;
import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.repository.OccasionRepository;
import cosmetics.backend.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OccasionService {
    private OccasionRepository occasionRepository;
    private UserRepository userRepository;

    @Autowired
    public OccasionService(OccasionRepository occasionRepository, UserRepository userRepository) {
        this.occasionRepository = occasionRepository;
        this.userRepository = userRepository;
    }

    public List<Occasion> getOccasions() { return occasionRepository.findAll(); }

    public Occasion addOccasion(Long userId) {
        Occasion occ = new Occasion();
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new RuntimeException("Could not found occasion with the given ID"));
        occ.setUser(user);
        occ.setStatus(Status.DONE);
        occ.setTimestamp(LocalDateTime.now());
        return occasionRepository.save(occ);
    }
}
