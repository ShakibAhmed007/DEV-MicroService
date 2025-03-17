package com.example.microservice.FilmAppDiscoveryService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().authenticated())
                .httpBasic(withDefaultsHttpBasicConfigure());
        return http.build();
    }

    private static Customizer<HttpBasicConfigurer<HttpSecurity>> withDefaultsHttpBasicConfigure() {
        return http -> {
        };
    }
}
