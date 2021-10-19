package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.*;
import cosmetics.backend.springboot.repository.OccasionRepository;
import cosmetics.backend.springboot.repository.OccasionTreatmentRepository;
import cosmetics.backend.springboot.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class OccasionTreatmentController {

    @Autowired
    private OccasionTreatmentRepository occasionTreatmentRepository;

    @Autowired
    private OccasionRepository occasionRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @GetMapping("occasiontreatments")
    private List<OccasionTreatment> getOccasionTreatments() {
        return this.occasionTreatmentRepository.findAll();
    }

    @PostMapping("occasionstreatment/{occasionId}/{treatmentId}")
    public OccasionTreatment addOccasionTreatment(@PathVariable Long occasionId, @PathVariable Long treatmentId){
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
