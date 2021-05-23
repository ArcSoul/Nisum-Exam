package cl.com.nisum.ws.technicalexam.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Añadiendo la configuración de Swagger
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

    @Value(value = "${security.header.authorization.key}")
    private static String HEADER_AUTHORIZATION_KEY;

    /**
     * @return Bean para configurar el Swagger
     */
    @Bean
    public OpenAPI springOpenAPI() {
        OpenAPI openAPI = new OpenAPI();

        /* Añadiendo un Componente de seguridad para el JWT Token */
        openAPI.components(new Components().addSecuritySchemes("Autenticación con Token JWT - Bearer",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER).name(HEADER_AUTHORIZATION_KEY)));

        /* Añadiendo el item de seguridad */
        openAPI.addSecurityItem(
                new SecurityRequirement().addList("Autenticación con Token JWT - Bearer", Arrays.asList("read", "write")));

        /* Añadiendo el información del Api */
        openAPI.info(new Info()
                .title("Technical Exam API - Nisum")
                .description("Technical Exam API Documentation, endpoints exposure")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));

        /* Añadiendo documentos externos */
        openAPI.externalDocs(new ExternalDocumentation()
                .description("Technical Exam Wiki Documentation")
                .url("https://github.com/ArcSoul/Nisum-Exam"));

        return openAPI;
    }
}
