package com.mandacode.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SessionFilter extends AbstractGatewayFilterFactory<SessionFilter.Config> {

  public SessionFilter() {
    super(SessionFilter.Config.class);
  }

  public static class Config {
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      return exchange.getSession().flatMap(session -> {
        if (session.getAttributes().get("uuid") != null) {
          String uuid = session.getAttributes().get("uuid").toString();

          // Add UUID to header
          return chain.filter(exchange.mutate().request(builder -> builder.header("x-uuid", uuid)).build());
        } else {
          exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
          exchange.getResponse().getHeaders().add("Content-Type", "application/json");
          exchange.getResponse().getHeaders().add("body", "Unauthorized");

          return exchange.getResponse().setComplete();
        }
      });
    };
  }
}
