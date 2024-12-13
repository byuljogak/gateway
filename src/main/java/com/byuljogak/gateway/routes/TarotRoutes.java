package com.byuljogak.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.byuljogak.gateway.config.RoutesConfig;
import com.byuljogak.gateway.filters.SessionFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class TarotRoutes {

  private final RoutesConfig routesConfig;
  private final SessionFilter sessionFilter;

  @Bean
  RouteLocator cloudRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes().route("tarot", r -> r.path("/tarot/**")
        .filters(f -> f.filter(sessionFilter.apply(sessionFilter.newConfig())))
        .uri(routesConfig.getTarotUri()))
        .build();
  }
}
