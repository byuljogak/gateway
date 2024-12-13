package com.byuljogak.gateway.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RoutesConfig {

  @Value("${routes.auth.uri}")
  private String authUri;

  @Value("${routes.tarot.uri}")
  private String tarotUri;
}
