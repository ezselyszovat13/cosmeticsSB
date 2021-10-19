package cosmetics.backend.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "occasion_treatment")
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Occasion getOccasion() {
        return occasion;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }

    @Override
    public String toString() {
        return "OccasionTreatment{" +
                "Id=" + Id +
                ", treatment=" + treatment +
                ", occasion=" + occasion +
                '}';
    }
}
