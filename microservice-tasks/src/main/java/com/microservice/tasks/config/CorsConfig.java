package com.microservice.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
            "http://localhost",
            "http://localhost:4200",
            "http://frontend",  // URL del contenedor frontend
            "http://localhost:80" // Puerto expuesto del frontend
        )); // Permitir Angular
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")); // Métodos permitidos
        config.setAllowedHeaders(List.of("*")); // Permitir todos los headers
        config.setAllowCredentials(true); // Permitir credenciales (tokens, cookies, etc.)

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}