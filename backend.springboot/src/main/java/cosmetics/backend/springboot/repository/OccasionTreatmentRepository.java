package cosmetics.backend.springboot.repository;

import cosmetics.backend.springboot.model.OccasionTreatment;
import cosmetics.backend.springboot.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OccasionTreatmentRepository extends JpaRepository<OccasionTreatment, Long> {
    @Query("SELECT ot.treatment FROM OccasionTreatment ot WHERE ot.occasion.id=?1")
    List<Treatment> findTreatmentsOfOccasion(Long id);
}
