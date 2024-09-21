package com.kuyajon.learningportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/*.webp", "/assets/**", "/index.html", "/").permitAll()  // Allow access to static resources including index.html
                                .anyRequest().authenticated() // Require authentication for other requests
                )
                .formLogin(withDefaults()) // Configure login form
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
