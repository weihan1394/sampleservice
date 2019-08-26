package com.govtech.sampleservice.service.message;

import com.govtech.datasource.externaldto.AbstractPLPDto;
import java.util.List;

public interface RabbitMqService {
  boolean sendMessages(List<? extends AbstractPLPDto> abstractPLPDto);

  boolean isAvailable();
}
