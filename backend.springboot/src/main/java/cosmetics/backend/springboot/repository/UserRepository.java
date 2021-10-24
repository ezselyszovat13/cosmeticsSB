package cosmetics.backend.springboot.repository;

import cosmetics.backend.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email=?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email=?1 and u.password=?2")
    public User findByEmailAndPassword(String email, String pw);
}
