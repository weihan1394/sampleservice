package com.govtech.sampleservice.repo.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "mysql")
public class DbProperties {
  private String host;
  private String schema;
  private String port;
  private String user;
  private String password;
}
