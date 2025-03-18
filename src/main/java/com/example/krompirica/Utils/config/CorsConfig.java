package com.example.krompirica.Utils.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(
                Arrays.asList(
                        "Origin",

                        "Access-Control-Allow-Origin",

                        "Content-Type",

                        "Accept",

                        "Authorization",

                        "Origin, Accept",

                        "X-Requested-With",

                        "Access-Control-Request-Method",

                        "Access-Control-Request-Headers"
                )
        );
        corsConfiguration.setExposedHeaders(
                Arrays.asList(
                        "Origin",

                        "Access-Control-Allow-Origin",

                        "Content-Type",

                        "Accept",

                        "Authorization",

                        "Origin, Accept",

                        "X-Requested-With",

                        "Access-Control-Request-Method",

                        "Access-Control-Request-Headers"
                )
        );
        corsConfiguration.addAllowedOrigin("http://localhost:4200");
        corsConfiguration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}