package cosmetics.backend.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int length;
    private int price;

    public Treatment() {
    }

    public Treatment(String name, int length, int price) {
        this.name = name;
        this.length = length;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public Set<OccasionTreatment> getOccasionTreatments(){
        return occasionTreatments;
    }
}
