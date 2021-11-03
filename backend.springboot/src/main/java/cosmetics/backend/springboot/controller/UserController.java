package cosmetics.backend.springboot.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import cosmetics.backend.springboot.dto.UserRoleRequest;
import cosmetics.backend.springboot.model.Role;
import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.service.UserService;
import cosmetics.backend.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${jwtSecret}")
    private String jwtSecret;

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("roles")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(userService.getRoles());
    }

    @PostMapping("users")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        if(tempEmail != null && !tempEmail.isEmpty()){
            User userObj = userService.getUser(tempEmail);
            if(userObj != null){
                throw new Exception("A megadott e-mail cím már használatban van!");
            }
        }
        else{
            throw new Exception("Az e-mail cím nem megfelelő!");
        }
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("roles/adduser")
    public ResponseEntity<?> addRoleToUser(@RequestBody UserRoleRequest userRoleRequest){
        userService.addRoleToUser(userRoleRequest.getEmail(),userRoleRequest.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String email = decodedJWT.getSubject();
                User user = userService.getUser(email);
                String accessToken = JWT.create().withSubject(email)
                        .withIssuer(request.getRequestURL().toString())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<String, String>();
                tokens.put("access_token",accessToken);
                tokens.put("refresh_token",refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            } catch (Exception e) {
                response.setHeader("error",e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error",e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        }
        else{
            throw new RuntimeException("Refresh token not found!");
        }
    }

}
