package awithd.finalproject.config;

import awithd.finalproject.service.UserService;
import awithd.finalproject.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
//    @Bean
//    public UserService userService(){
//        return new UserService();
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);

//        authenticationManagerBuilder
//                .userDetailsService(UserService())
//                .passwordEncoder(passwordEncoder());

        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers(
                        "/api/auth/**",
                        "/api/docs",
                        "/api/swagger-ui/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                )
                .permitAll()
                .anyRequest().authenticated()
        );
        http.httpBasic(Customizer.withDefaults());
//        http.httpBasic(httpBasic -> httpBasic.disable());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
