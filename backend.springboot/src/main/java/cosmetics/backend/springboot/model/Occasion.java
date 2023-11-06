package cosmetics.backend.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "occasions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Occasion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getUserId(){
        return user.getId();
    }

    private Status status;
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "Occasion{" +
                "id=" + id +
                ", user=" + user +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonIgnore
    @OneToMany(mappedBy = "occasion")
    private Set<OccasionTreatment> occasionTreatments = new HashSet<>();
}
