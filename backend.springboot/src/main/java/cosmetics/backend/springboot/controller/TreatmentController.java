package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.Treatment;
import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.service.TreatmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class TreatmentController {
    private TreatmentServiceImpl treatmentService;

    @Autowired
    public TreatmentController(TreatmentServiceImpl treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping("treatments")
    public ResponseEntity<List<Treatment>> getTreatments(){
        return ResponseEntity.ok(treatmentService.getTreatments());
    }

    @GetMapping("treatments/{treatmentId}")
    public ResponseEntity<?> getTreatmentById(@PathVariable("treatmentId") Long treatmentId) {
        Treatment treatment;
        try{
            treatment = treatmentService.getTreatmentById(treatmentId);
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.ok(treatment);
    }

    @PostMapping("treatments")
    public ResponseEntity<?> saveTreatment(@RequestBody Treatment treatment) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/treatments").toUriString());
        try{
            treatmentService.saveTreatment(treatment);
        }
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.created(uri).body(treatment);
    }
}
