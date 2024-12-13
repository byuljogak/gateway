package com.byuljogak.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.byuljogak.gateway.config.RoutesConfig;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class AuthRoutes {

  private final RoutesConfig routesConfig;

  @Bean
  RouteLocator authRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes().route("auth", r -> r.path("/auth/**")
        .uri(routesConfig.getAuthUri()))
        .build();
  }
}
