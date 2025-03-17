package com.agency.just.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Value("${seletivo.swagger.url}")
    private String urlApp;
    
    @Value("${server.servlet.context-path}")
    private String apiPath;


    @Bean
    public OpenAPI customAopenApi() {
        String schemeName = "bearerAuth";
        System.out.println("urlApp:"+urlApp);
        System.out.println("apiPath:"+apiPath);
        return new OpenAPI()
                .addServersItem(new Server().url(urlApp + apiPath))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")) // Especifique o formato, como JWT
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(new Info()
                        .title("API Example")
                        .description("Descrição da API")
                        .contact(new Contact().email("email@gmail.com"))
                );
    }

}