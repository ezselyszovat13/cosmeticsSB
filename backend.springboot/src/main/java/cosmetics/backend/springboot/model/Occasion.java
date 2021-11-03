package cosmetics.backend.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "occasions")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

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

    public Set<OccasionTreatment> getOccasionTreatments(){
        return occasionTreatments;
    }
}
