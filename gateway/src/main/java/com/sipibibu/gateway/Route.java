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
    @Value("${services.messenger}")
    String messUrl;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/auth/**")
//                        .uri("http://localhost:8090"))
                        .uri(authUrl))
                .route(p -> p
                        .path("/account/**")
//                        .uri("http://localhost:8090"))
                        .uri(authUrl))
                .route(p -> p
                        .path("/company/**")
//                        .uri("http://localhost:8090"))
                        .uri(authUrl))
                .route(p -> p
                        .path("/interests/**")
//                        .uri("http://localhost:8090"))
                        .uri(authUrl))
                .route(p -> p
                        .path("/forms/**")
                        //.uri("http://localhost:8012"))
                        .uri(apiUrl))
                .route(p -> p
                        .path("/quests/**")
                        //.uri("http://localhost:8012"))
                        .uri(apiUrl))
                .route(p -> p
                        .path("/answers/**")
                        //.uri("http://localhost:8012"))
                        .uri(apiUrl))
                .route(p -> p
                        .path("/chats/**")
                        //.uri("http://localhost:8012"))
                        .uri(messUrl))
                .route(p -> p
                        .path("/messages/**")
                        //.uri("http://localhost:8012"))
                        .uri(messUrl))
                .build();
    }
}
