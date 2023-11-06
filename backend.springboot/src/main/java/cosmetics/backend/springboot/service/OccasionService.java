package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.model.Status;
import cosmetics.backend.springboot.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OccasionService {
    List<Occasion> getAllOccasions();
    //List<Occasion> getOccasionsOfUserById(Long userId);
    Occasion reserveOccasion(Long userId);
    Occasion getOccasionById(Long occId);
    Occasion acceptOccasion(Long occasionId);
    Occasion declineOccasion(Long occasionId);
    Occasion finalizeOccasion(Long occasionId);

}
