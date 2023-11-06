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
import java.util.Optional;

@Service
public class OccasionServiceImpl implements OccasionService {
    @Autowired
    private OccasionRepository occasionRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Occasion> getAllOccasions() { return occasionRepository.findAll(); }

    /*@Override
    public List<Occasion> getOccasionsOfUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new IllegalStateException("A megadott azonosítóval felhasználó nem található!");
        }
        return occasionRepository.getOccasionsOfUserById(userId);
    }*/

    public Occasion reserveOccasion(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new IllegalStateException("A megadott azonosítóval felhasználó nem található!");
        }
        Occasion occ = new Occasion();
        occ.setUser(user.get());
        occ.setStatus(Status.RESERVED);
        occ.setTimestamp(LocalDateTime.now());
        return occasionRepository.save(occ);
    }

    public Occasion acceptOccasion(Long occasionId) {
        Optional<Occasion> actualOccasion = occasionRepository.findById(occasionId);
        if(!actualOccasion.isPresent()){
            throw new IllegalStateException("A megadott azonosítójú alkalom nem található!");
        }
        Occasion occ = actualOccasion.get();
        occ.setStatus(Status.ACCEPTED);
        return occasionRepository.save(occ);
    }

    public Occasion declineOccasion(Long occasionId) {
        Optional<Occasion> actualOccasion = occasionRepository.findById(occasionId);
        if(!actualOccasion.isPresent()){
            throw new IllegalStateException("A megadott azonosítójú alkalom nem található!");
        }
        Occasion occ = actualOccasion.get();
        occ.setStatus(Status.DECLINED);
        return occasionRepository.save(occ);
    }

    public Occasion finalizeOccasion(Long occasionId) {
        Optional<Occasion> actualOccasion = occasionRepository.findById(occasionId);
        if(!actualOccasion.isPresent()){
            throw new IllegalStateException("A megadott azonosítójú alkalom nem található!");
        }
        Occasion occ = actualOccasion.get();
        occ.setStatus(Status.DONE);
        return occasionRepository.save(occ);
    }

    @Override
    public Occasion getOccasionById(Long occId) {
        Optional<Occasion> actualOccasion = occasionRepository.findById(occId);
        if(!actualOccasion.isPresent()){
            throw new IllegalStateException("A megadott azonosítójú alkalom nem található!");
        }
        return actualOccasion.get();
    }
}
