package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Role;
import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.repository.RoleRepository;
import cosmetics.backend.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new IllegalStateException("A megadott e-mail cím már használatban van!");
        }
        String email = user.getEmail();
        if(email.length() > 70){
            throw new IllegalArgumentException("Az e-mail maximum 70 karakter hosszú lehet!");
        }
        if(email.isEmpty()){
            throw new IllegalArgumentException("Az e-mail cím megadása kötelező!");
        }
        if(user.getPassword().isEmpty()){
            throw new IllegalArgumentException("A jelszó megadása kötelező!");
        }
        if(user.getFirstName().isEmpty() || user.getLastName().isEmpty()){
            throw new IllegalArgumentException("A vezeték és keresztnév megadása kötelező!");
        }
        if(user.getAddress().isEmpty()){
            throw new IllegalArgumentException("A cím megadása kötelező!");
        }
        if(user.getTel().isEmpty()){
            throw new IllegalArgumentException("A telefonszám megadása kötelező!");
        }

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!email.matches(regexPattern)){
            throw new IllegalArgumentException("Nem megfelelő az e-mail cím formátuma!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        addRoleToUser(user.getEmail(),"ROLE_USER");
        return user;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        Optional<User> user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.get().getRoles().add(role);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if(!userByEmail.isPresent()){
            throw new IllegalStateException("Az e-mail címmel felhasználó nem található!");
        }
        return userByEmail.get();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userByEmail = userRepository.findById(id);
        if(!userByEmail.isPresent()){
            throw new IllegalStateException("A megadott azonosítójú felhasználó nem található!");
        }
        return userByEmail.get();
    }

    @Override
    public List<User> getUsers() { return userRepository.findAll(); }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password){
        Optional<User> userByEmail = userRepository.findByEmailAndPassword(email, password);
        if(!userByEmail.isPresent()){
            throw new IllegalStateException("A megadott e-mail címmel és jelszóval felhasználó nem található!");
        }
        return userByEmail.get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            throw new UsernameNotFoundException("Az e-mail címmel felhasználó nem található!");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        User actualUser = user.get();
        for (Role role : actualUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(actualUser.getEmail(), actualUser.getPassword(), authorities);
    }
}