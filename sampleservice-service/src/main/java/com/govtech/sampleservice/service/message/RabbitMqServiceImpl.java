package com.govtech.sampleservice.service.message;

import com.govtech.datasource.externaldto.AbstractPLPDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements RabbitMqService {

  @Autowired
  MessageChannelFactory messageChannelFactory;

  @Override
  public boolean sendMessages(List<? extends AbstractPLPDto> abstractPLPDtoList) {
    boolean overallStatus = true;

    for (AbstractPLPDto abstractPLPDto: abstractPLPDtoList) {
      MessageChannel messageChannel = messageChannelFactory.create(abstractPLPDto.getClass());

      boolean send = messageChannel.send(MessageBuilder.withPayload(abstractPLPDto).build());
      if (!send) {
        overallStatus = false;
      }
    }

    return overallStatus;
  }

  @Override
  public boolean isAvailable() {
    return true;
  }
}
