package br.com.robertoxavier.api.config;

import io.swagger.v3.oas.models.media.Schema;
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

import java.time.LocalDate;

@Configuration
public class OpenApiConfig {

    @Value("${seletivo.swagger.url}")
    private String urlApp;
    
    @Value("${server.servlet.context-path}")
    private String apiPath;


    @Bean
    public OpenAPI customAopenApi() {
        String schemeName = "bearerAuth";
        return new OpenAPI()
                .addServersItem(new Server().url(urlApp + apiPath))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                        .addSchemas("LocalDate", new Schema<LocalDate>()
                                .type("string")
                                .example("26/03/2025"))
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(new Info()
                        .title("API Seletivo SEPLAG")
                        .description("API para o Seletivo da SEPLAG")
                        .contact(new Contact().email("roberto.sxav@gmail.com"))
                );
    }

}