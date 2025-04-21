package upc.edu.ecomovil.api.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
        @Bean
        public OpenAPI ecomovilOpenApi() {
            // General configuration
            var openApi = new OpenAPI();
            openApi.info(new Info()
                    .title("UPC Ecomovil API")
                    .description("UPC Ecomovil application RESTful API documentation")
                    .version("1.0.0")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                    .externalDocs(new ExternalDocumentation()
                            .description("UPC Ecomovil API Wiki Documentation")
                            .url("https://upc-ecomovil-api.wiki.github.io/docs"));
            // Add security scheme
            final String securitySchemeName = "bearerAuth";

        openApi.addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));

            // Return OpenAPI configuration object with all the settings

            return openApi;
        }

}
