package com.govtech.sampleservice.service.message;

import com.govtech.datasource.externaldto.AbstractPLPDto;
import com.govtech.datasource.externaldto.BatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class MessageChannelFactory {

  @Autowired PreparedDataSource preparedDataSource;

  public MessageChannel create(Class<? extends AbstractPLPDto> type) {

    if (type.equals(BatchDto.class)) {
      MessageChannel messageChannel = preparedDataSource.batchChannel();
      return messageChannel;
    }

    return null;
  }

}
