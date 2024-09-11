package com.bezkoder.spring.jpa.h2.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Autoriser toutes les origines
        config.addAllowedOrigin("*");

        // Autoriser toutes les méthodes (GET, POST, etc.)
        config.addAllowedMethod("*");

        // Autoriser tous les headers
        config.addAllowedHeader("*");

        // Appliquer les configurations ci-dessus à toutes les routes
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
