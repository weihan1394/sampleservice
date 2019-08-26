package com.govtech.sampleservice.service.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PreparedDataSource {

  @Output("batch_channel")
  MessageChannel batchSource();
}
