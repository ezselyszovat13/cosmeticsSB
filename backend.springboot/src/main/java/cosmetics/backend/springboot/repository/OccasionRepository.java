package cosmetics.backend.springboot.repository;

import cosmetics.backend.springboot.model.Occasion;
import cosmetics.backend.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {
    /*@Query("SELECT o FROM Occasion o INNER JOIN User u ON o.user_id = u.id WHERE u.id=?1")
    List<Occasion> getOccasionsOfUserById(Long userId);*/
}
