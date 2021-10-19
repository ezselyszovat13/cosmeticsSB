package cosmetics.backend.springboot.repository;

import cosmetics.backend.springboot.model.OccasionTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionTreatmentRepository extends JpaRepository<OccasionTreatment, Long> {
}
