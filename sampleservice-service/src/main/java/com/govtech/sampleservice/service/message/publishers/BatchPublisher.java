package com.govtech.sampleservice.service.message.publishers;

import com.govtech.datasource.externaldto.BatchDto;
import com.govtech.sampleservice.service.message.PreparedDataSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(PreparedDataSource.class)
public class BatchPublisher {

  @Bean
  public MessageSource<BatchDto> sendBatch() {
    return () -> MessageBuilder.withPayload(new BatchDto("", "")).build();
  }
}
