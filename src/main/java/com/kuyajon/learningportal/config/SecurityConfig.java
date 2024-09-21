package com.kuyajon.learningportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private ProfileConfig profileConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (profileConfig.getEnvironment() == EEnvironment.DEV) {
            http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                            .requestMatchers("**").permitAll()
                            .anyRequest().authenticated())
                    .formLogin(withDefaults())
                    .logout(logout -> logout.permitAll());
        } else {
            http
                    .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                            .requestMatchers("/*.webp", "/assets/**", "/index.html", "/").permitAll()
                            .anyRequest().authenticated())
                    .formLogin(withDefaults())
                    .logout(logout -> logout.permitAll());
        }

        return http.build();
    }
}
