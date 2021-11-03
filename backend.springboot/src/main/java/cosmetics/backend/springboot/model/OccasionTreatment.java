package cosmetics.backend.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "occasion_treatment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OccasionTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "treatment_id", referencedColumnName = "id")
    private Treatment treatment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "occasion_id", referencedColumnName = "id")
    private Occasion occasion;

    @Override
    public String toString() {
        return "OccasionTreatment{" +
                "Id=" + Id +
                ", treatment=" + treatment +
                ", occasion=" + occasion +
                '}';
    }
}
