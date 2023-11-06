package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.model.OccasionTreatment;
import cosmetics.backend.springboot.model.Treatment;

import java.util.List;

public interface OccasionTreatmentService {
    List<OccasionTreatment> getOccasionTreatments();
    List<Treatment> getTreatmentsOfOccsaion(Long id);
    OccasionTreatment addOccasionTreatment(Long occasionId, Long treatmentId);
}
