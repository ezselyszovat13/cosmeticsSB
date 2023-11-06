package cosmetics.backend.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "treatments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int length;
    private int price;

    public Treatment(String name, int length, int price) {
        this.name = name;
        this.length = length;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", price=" + price +
                '}';
    }

    @JsonIgnore
    @OneToMany(mappedBy = "treatment")
    private Set<OccasionTreatment> occasionTreatments = new HashSet<>();
}
