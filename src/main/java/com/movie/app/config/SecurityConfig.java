package com.movie.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //user credentials wordt in inmemory geinjecteerd
    @Bean
    public UserDetailsService userDetailService(PasswordEncoder encoder) {
        InMemoryUserDetailsManager man = new InMemoryUserDetailsManager();
        UserDetails ud1 = User
                .withUsername("karel")
                .password(encoder.encode("pass"))
                .roles("USER")
                .build();
        UserDetails ud2 = User
                .withUsername("henk")
                .password(encoder.encode("name"))
                .roles("ADMIN")
                .build();
        man.createUser(ud1);
        man.createUser(ud2);
        return man;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                                //permit all hoeft niet ingelogd accepteert iedereen
                                .requestMatchers("/movie").permitAll()
                                .requestMatchers(HttpMethod.POST, "/movie").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/votes").hasRole("USER")
//                                .requestMatchers("/movie").hasRole("ADMIN")
//                        .hasAnyRole("USER", "ADMIN")
//                                .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}