package com.microservice.users.connector.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        //final String securitySchemeName = "Bearer Token";

        return new OpenAPI()
                .info(
                        new Info().title("Microservicio de Usuarios")
                                .description("API documentación de las distintas funcionalidades del microservicio de usuarios ")
                                .version("1.0")
                );
                /*.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .tags(List.of(
                        new Tag().name("API de gestión de autenticación y usuarios").description("Autenticación y usuarios")
                ))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));*/
    }


}