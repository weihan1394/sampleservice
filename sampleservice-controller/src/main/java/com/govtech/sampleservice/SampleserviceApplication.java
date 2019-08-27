package com.govtech.sampleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SampleserviceApplication {
  public static void main(String[] args) {
    SpringApplication.run(SampleserviceApplication.class, args);
  }

  @StreamListener(target = Sink.INPUT)
  public void processBatch(String batchOutput) {
    System.out.println("consumer: " + batchOutput);
  }
}
