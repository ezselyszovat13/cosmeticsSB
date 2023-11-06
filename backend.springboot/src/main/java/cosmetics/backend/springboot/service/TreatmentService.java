package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Treatment;
import cosmetics.backend.springboot.model.User;

import java.util.List;

public interface TreatmentService {
    List<Treatment> getTreatments();
    Treatment saveTreatment(Treatment treatment);
    Treatment getTreatmentById(Long id);
}
