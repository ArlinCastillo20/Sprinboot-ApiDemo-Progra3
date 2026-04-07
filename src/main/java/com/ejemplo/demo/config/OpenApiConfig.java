package com.ejemplo.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Demo Spring Boot")
                        .version("1.0.0")
                        .description("API del laboratorio de Programación III")
                        .contact(new Contact()
                                .name("Arlin Guisel Castillo Cermeño")
                                .email("acastilloc31@miumg.edu.gt")));
    }
}