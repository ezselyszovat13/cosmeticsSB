package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.*;
import cosmetics.backend.springboot.service.OccasionTreatmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class OccasionTreatmentController {
    private OccasionTreatmentServiceImpl occasionTreatmentService;

    @Autowired
    public OccasionTreatmentController(OccasionTreatmentServiceImpl occasionTreatmentService) {
        this.occasionTreatmentService = occasionTreatmentService;
    }

    @GetMapping("occasiontreatments")
    private List<OccasionTreatment> getOccasionTreatments() {
        return this.occasionTreatmentService.getOccasionTreatments();
    }

    @GetMapping("occasiontreatments/{occasionId}")
    private List<Treatment> getTreatmentsOfOccasion(@PathVariable Long occasionId) {
        return this.occasionTreatmentService.getTreatmentsOfOccsaion(occasionId);
    }

    @PostMapping("occasiontreatments/{occasionId}/{treatmentId}")
    public OccasionTreatment addOccasionTreatment(@PathVariable Long occasionId, @PathVariable Long treatmentId){
        return this.occasionTreatmentService.addOccasionTreatment(occasionId, treatmentId);
    }
}
