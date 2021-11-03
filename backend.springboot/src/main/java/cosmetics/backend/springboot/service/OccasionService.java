package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.model.Status;
import cosmetics.backend.springboot.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OccasionService {
    List<Occasion> getOccasions();
    Occasion addOccasion(Long userId);
    Occasion getOccasion(Long occId);
}
