package cosmetics.backend.springboot.repository;

import cosmetics.backend.springboot.model.Treatment;
import cosmetics.backend.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    @Query("SELECT t FROM Treatment t WHERE t.name=?1")
    Optional<Treatment> findByName(String name);
}
