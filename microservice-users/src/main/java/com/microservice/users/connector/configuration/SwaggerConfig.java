package com.microservice.users.connector.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info().title("Microservicio de Usuarios")
                                .description("API documentación de las distintas funcionalidades del microservicio de usuarios ")
                                .version("1.0")
                );
    }


}