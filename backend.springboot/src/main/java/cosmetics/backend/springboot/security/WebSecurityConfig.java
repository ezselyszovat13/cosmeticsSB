package cosmetics.backend.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), jwtSecret);
        authenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/login/**").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers( "/roles").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/roles/**").hasAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/treatments").hasAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/occasions").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/occasions/occbyid/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/occasions/acc/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/occasions/dec/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/occasions/fin/**").hasAuthority("ROLE_ADMIN");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(authenticationFilter);
        http.addFilterBefore(new AuthorizationFilter(jwtSecret), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
}
