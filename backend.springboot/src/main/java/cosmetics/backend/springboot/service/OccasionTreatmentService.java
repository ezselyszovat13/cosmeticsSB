package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.model.OccasionTreatment;
import cosmetics.backend.springboot.model.Treatment;
import cosmetics.backend.springboot.repository.OccasionRepository;
import cosmetics.backend.springboot.repository.OccasionTreatmentRepository;
import cosmetics.backend.springboot.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class OccasionTreatmentService {
    private OccasionTreatmentRepository occasionTreatmentRepository;
    private OccasionRepository occasionRepository;
    private TreatmentRepository treatmentRepository;

    @Autowired
    public OccasionTreatmentService(OccasionTreatmentRepository occasionTreatmentRepository, OccasionRepository occasionRepository, TreatmentRepository treatmentRepository) {
        this.occasionTreatmentRepository = occasionTreatmentRepository;
        this.occasionRepository = occasionRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public List<OccasionTreatment> getOccasionTreatments() {
        return this.occasionTreatmentRepository.findAll();
    }

    public OccasionTreatment addOccasionTreatment(Long occasionId, Long treatmentId){
        Occasion occ = occasionRepository.findById(occasionId).orElseThrow(
                ()-> new RuntimeException("Could not found occasion with the given ID"));
        Treatment treatment = treatmentRepository.findById(treatmentId).orElseThrow(
                ()-> new RuntimeException("Could not found treatment with the given ID"));

        OccasionTreatment occtreat = new OccasionTreatment();
        occtreat.setOccasion(occ);
        occtreat.setTreatment(treatment);

        return occasionTreatmentRepository.save(occtreat);
    }
}
