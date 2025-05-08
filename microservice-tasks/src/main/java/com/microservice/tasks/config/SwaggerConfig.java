package com.microservice.tasks.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info().title("Microservicio de Tareas")
                                .description("API documentación de las distintas funcionalidades del microservicio de tareas")
                                .version("1.0")
                );
    }
}