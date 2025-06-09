package upc.edu.ecomovil.api.shared.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Global CORS Configuration for EcoMovil API
 * 
 * This configuration handles CORS for different deployment environments:
 * - Local development
 * - AWS App Runner 
 * - AWS ECS Fargate
 * - Microservices architecture
 */
@Configuration
public class GlobalCorsConfiguration {

    @Value("${cors.allowed-origins:*}")
    private String allowedOrigins;

    @Value("${cors.allowed-methods:GET,POST,PUT,DELETE,PATCH,OPTIONS}")
    private String allowedMethods;

    @Value("${cors.allow-credentials:true}")
    private boolean allowCredentials;

    @Value("${cors.max-age:3600}")
    private long maxAge;

    /**
     * Global CORS configuration bean for microservices
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        
        // Dynamic origins based on environment
        if ("*".equals(allowedOrigins)) {
            // For development and flexible environments
            configuration.setAllowedOriginPatterns(List.of(
                "*", // Development wildcard
                "https://*.awsapprunner.com", // App Runner
                "https://*.amazonaws.com", // AWS services
                "https://*.elb.amazonaws.com", // Load balancers
                "http://localhost:*", // Local development
                "https://localhost:*", // Local HTTPS
                "http://127.0.0.1:*", // Local IP
                "https://127.0.0.1:*" // Local HTTPS IP
            ));
        } else {
            // For production with specific domains
            configuration.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
        }
        
        configuration.setAllowedMethods(Arrays.asList(allowedMethods.split(",")));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(allowCredentials);
        configuration.setExposedHeaders(List.of(
            "Authorization", 
            "Content-Type", 
            "X-Total-Count",
            "X-Request-ID"
        ));
        configuration.setMaxAge(maxAge);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
