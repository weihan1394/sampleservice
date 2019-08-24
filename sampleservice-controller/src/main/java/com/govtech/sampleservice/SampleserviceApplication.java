package com.govtech.sampleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SampleserviceApplication {
  public static void main(String[] args) {
    SpringApplication.run(SampleserviceApplication.class, args);
  }
}
