package com.sipibibu.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Route {
    @Value("${services.auth}")
    String authUrl;
    @Value("${services.api}")
    String apiUrl;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/auth/**")
//                        .uri("http://localhost:8090"))
                        .uri(authUrl))
                .route(p -> p
                        .path("/api/**")
                        //.uri("http://localhost:8012"))
                        .uri(apiUrl))
                .build();
    }
}
