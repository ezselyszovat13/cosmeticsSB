package cosmetics.backend.springboot;

import cosmetics.backend.springboot.controller.OccasionController;
import cosmetics.backend.springboot.controller.OccasionTreatmentController;
import cosmetics.backend.springboot.model.*;
import cosmetics.backend.springboot.repository.OccasionRepository;
import cosmetics.backend.springboot.repository.OccasionTreatmentRepository;
import cosmetics.backend.springboot.repository.TreatmentRepository;
import cosmetics.backend.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OccasionRepository occasionRepository;
	@Autowired
	private TreatmentRepository treatmentRepository;
	@Autowired
	private OccasionTreatmentRepository occasionTreatmentRepository;

	@Autowired
	private OccasionTreatmentController occasionTreatmentController;

	@Autowired
	private OccasionController occasionController;

	@Override
	public void run(String... args) throws Exception {
		User user1 = this.userRepository.save(new User("Béla","Nagy","bela.nagy","belanagy@gmail.com","password","06202792722","9145 Jancsiföld, Kacsa utca 2."));
		User user2 = this.userRepository.save(new User("Ferenc","Kiss","ferenc.kiss","ferenckiss@gmail.com","password","+36302792722","1015 Budapest, Ostrom utca 2."));
		User user3 = this.userRepository.save(new User("József","Horváth","jozsef.horvath","jozsefhorvath@gmail.com","password","+36302794522","9021 Győr, Lajta út 22."));


		Treatment treatment1 = this.treatmentRepository.save(new Treatment("Kiskezelés",30,1000));
		Treatment treatment2 = this.treatmentRepository.save(new Treatment("Nagykezelés",60,2000));
		Treatment treatment3 = this.treatmentRepository.save(new Treatment("Szemöldökszedés",10, 500));

		Occasion occ1 = this.occasionRepository.save(occasionController.addOccasion(user1.getId()));

		this.occasionTreatmentRepository.save(occasionTreatmentController.addOccasionTreatment(treatment1.getId(),occ1.getId()));

		Occasion occ2 = this.occasionRepository.save(occasionController.addOccasion(user2.getId()));
		this.occasionTreatmentRepository.save(occasionTreatmentController.addOccasionTreatment(treatment1.getId(),occ2.getId()));
		this.occasionTreatmentRepository.save(occasionTreatmentController.addOccasionTreatment(treatment2.getId(),occ2.getId()));

		Occasion occ3 = this.occasionRepository.save(occasionController.addOccasion(user3.getId()));
		this.occasionTreatmentRepository.save(occasionTreatmentController.addOccasionTreatment(treatment3.getId(),occ3.getId()));
	}
}
