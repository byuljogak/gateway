package com.mandacode.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mandacode.gateway.config.RoutesConfig;
import com.mandacode.gateway.filters.SessionFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class CloudRoutes {

  private final RoutesConfig routesConfig;
  private final SessionFilter sessionFilter;

  @Bean
  RouteLocator cloudRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes().route("cloud", r -> r.path("/cloud/**")
        .filters(f -> f.filter(sessionFilter.apply(sessionFilter.newConfig())))
        .uri(routesConfig.getCloudUri()))
        .build();
  }
}
