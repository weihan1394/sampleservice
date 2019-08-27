package com.govtech.sampleservice.service.message.consumers;

import com.govtech.datasource.externaldto.BatchDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BatchConsumer extends AbstractConsumer {
  @RabbitListener(queues = "batchChannel.batchQueue")
  public void processBatchQueueMessage(BatchDto batchDto) {
    System.out.println("incoming message: " + batchDto.getName() + " " + batchDto.getNric());
  }

}
