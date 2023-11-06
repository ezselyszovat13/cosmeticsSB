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
public class OccasionTreatmentServiceImpl implements OccasionTreatmentService{
    @Autowired
    private OccasionTreatmentRepository occasionTreatmentRepository;
    @Autowired
    private OccasionRepository occasionRepository;
    @Autowired
    private TreatmentRepository treatmentRepository;

    public List<OccasionTreatment> getOccasionTreatments() {
        return this.occasionTreatmentRepository.findAll();
    }

    public List<Treatment> getTreatmentsOfOccsaion(Long id) {
        return this.occasionTreatmentRepository.findTreatmentsOfOccasion(id);
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
