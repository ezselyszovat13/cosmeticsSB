package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.*;
import cosmetics.backend.springboot.repository.OccasionRepository;
import cosmetics.backend.springboot.repository.OccasionTreatmentRepository;
import cosmetics.backend.springboot.repository.TreatmentRepository;
import cosmetics.backend.springboot.service.OccasionService;
import cosmetics.backend.springboot.service.OccasionTreatmentService;
import cosmetics.backend.springboot.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class OccasionTreatmentController {
    private OccasionTreatmentService occasionTreatmentService;

    @Autowired
    public OccasionTreatmentController(OccasionTreatmentService occasionTreatmentService) {
        this.occasionTreatmentService = occasionTreatmentService;
    }

    @GetMapping("occasiontreatments")
    private List<OccasionTreatment> getOccasionTreatments() {
        return this.occasionTreatmentService.getOccasionTreatments();
    }

    @PostMapping("occasionstreatment/{occasionId}/{treatmentId}")
    public OccasionTreatment addOccasionTreatment(@PathVariable Long occasionId, @PathVariable Long treatmentId){
        return this.occasionTreatmentService.addOccasionTreatment(occasionId, treatmentId);
    }
}
