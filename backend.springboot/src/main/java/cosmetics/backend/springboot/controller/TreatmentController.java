package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.Treatment;
import cosmetics.backend.springboot.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class TreatmentController {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @GetMapping("treatments")
    public List<Treatment> getTreatments() { return this.treatmentRepository.findAll(); }
}
