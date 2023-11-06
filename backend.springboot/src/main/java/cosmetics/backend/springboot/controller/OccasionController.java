package cosmetics.backend.springboot.controller;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.service.OccasionServiceImpl;
import cosmetics.backend.springboot.service.UserServiceImpl;
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
public class OccasionController {
    private OccasionServiceImpl occasionService;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public OccasionController(OccasionServiceImpl occasionService, UserServiceImpl userServiceImpl) {
        this.occasionService = occasionService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("occasions")
    public List<Occasion> getOccasions() { return this.occasionService.getAllOccasions(); }

    @GetMapping("occasions/occbyid/{occasionId}")
    public ResponseEntity<?> getOccasionById(@PathVariable("occasionId") Long occasionId) {
        Occasion occasion;
        try{
            occasion = occasionService.getOccasionById(occasionId);
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.ok(occasion);
    }

    @PostMapping("occasions/{userId}")
    public ResponseEntity<?> reserveOccasion(@PathVariable("userId") Long userId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/occasions/{userId}").toUriString());
        Occasion occasion;
        try{
            occasion = occasionService.reserveOccasion(userId);
        }
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.created(uri).body(occasion);
    }

    /*@GetMapping("occasions/{userId}")
    public ResponseEntity<?> getOccasionsOfUser(@PathVariable("userId") Long userId) {
        List<Occasion> occasions;
        try{
            occasions = occasionService.getOccasionsOfUserById(userId);
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.ok(occasions);
    }*/

    @PutMapping("occasions/acc/{occasionId}")
    public ResponseEntity<?> acceptOccasion(@PathVariable("occasionId") Long occasionId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/occasions/acc/{occasionId}").toUriString());
        Occasion occasion;
        try{
            occasion = occasionService.acceptOccasion(occasionId);
        }
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.created(uri).body(occasion);
    }

    @PutMapping("occasions/dec/{occasionId}")
    public ResponseEntity<?> declineOccasion(@PathVariable("occasionId") Long occasionId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/occasions/dec/{occasionId}").toUriString());
        Occasion occasion;
        try{
            occasion = occasionService.declineOccasion(occasionId);
        }
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.created(uri).body(occasion);
    }

    @PutMapping("occasions/fin/{occasionId}")
    public ResponseEntity<?> finalizeOccasion(@PathVariable("occasionId") Long occasionId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/occasions/fin/{occasionId}").toUriString());
        Occasion occasion;
        try{
            occasion = occasionService.finalizeOccasion(occasionId);
        }
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.created(uri).body(occasion);
    }
}
