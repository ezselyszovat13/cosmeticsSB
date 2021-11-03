package cosmetics.backend.springboot;

import cosmetics.backend.springboot.model.*;
import cosmetics.backend.springboot.service.OccasionServiceImpl;
import cosmetics.backend.springboot.service.OccasionTreatmentServiceImpl;
import cosmetics.backend.springboot.service.TreatmentServiceImpl;
import cosmetics.backend.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private UserService userService;
	@Autowired
	private OccasionServiceImpl occasionService;
	@Autowired
	private OccasionTreatmentServiceImpl occasionTreatmentService;
	@Autowired
	private TreatmentServiceImpl treatmentService;

	@Override
	public void run(String... args) throws Exception {
		User user1 = userService.saveUser(new User("Admin","Admin","admin@gmail.com","password","+36201111111","1015 Budapest, Admin utca 69."));
		User user2 = userService.saveUser(new User("Ferenc","Kiss","ferenckiss@gmail.com","password","+36302792722","1015 Budapest, Ostrom utca 2."));
		User user3 = userService.saveUser(new User("József","Horváth","jozsefhorvath@gmail.com","password","+36302794522","9021 Győr, Lajta út 22."));
		User user4 = userService.saveUser(new User("Béla","Nagy","belanagy@gmail.com","password","06202792722","9145 Jancsiföld, Kacsa utca 2."));;

		Role role1 = userService.saveRole(new Role(null,"ROLE_USER"));
		Role role2 = userService.saveRole(new Role(null,"ROLE_ADMIN"));

		userService.addRoleToUser("admin@gmail.com", "ROLE_ADMIN");
		userService.addRoleToUser("ferenckiss@gmail.com", "ROLE_USER");
		userService.addRoleToUser("jozsefhorvath@gmail.com", "ROLE_USER");
		userService.addRoleToUser("belanagy@gmail.com", "ROLE_USER");

		Treatment treatment1 = treatmentService.saveTreatment(new Treatment("Kiskezelés",30,1000));
		Treatment treatment2 = treatmentService.saveTreatment(new Treatment("Nagykezelés",60,2000));
		Treatment treatment3 = treatmentService.saveTreatment(new Treatment("Szemöldökszedés",10, 500));

		Occasion occ1 = occasionService.addOccasion(user1.getId());
		occasionTreatmentService.addOccasionTreatment(treatment1.getId(),occ1.getId());

		Occasion occ2 = occasionService.addOccasion(user2.getId());
		occasionTreatmentService.addOccasionTreatment(treatment1.getId(),occ2.getId());
		occasionTreatmentService.addOccasionTreatment(treatment2.getId(),occ2.getId());

		Occasion occ3 = occasionService.addOccasion(user3.getId());
		occasionTreatmentService.addOccasionTreatment(treatment3.getId(),occ3.getId());
	}
}
