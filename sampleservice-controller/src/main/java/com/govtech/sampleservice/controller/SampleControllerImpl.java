package com.govtech.sampleservice.controller;

import com.govtech.datasource.externaldto.BatchDto;
import com.govtech.sampleservice.service.dtos.SampleDto;
import com.govtech.sampleservice.service.message.RabbitMqService;
import com.govtech.sampleservice.service.services.SampleService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sample")
public class SampleControllerImpl implements SampleController {
  @Autowired SampleService sampleService;
  @Autowired RabbitMqService rabbitMqService;

  @GetMapping(value = "/sampleApi")
  public ResponseEntity sampleApi() {
    SampleDto sampleObj = sampleService.getSampleDto();
    return new ResponseEntity(sampleObj, HttpStatus.OK);
  }

  @GetMapping(value = "/sendMessage")
  public ResponseEntity sendMessage() {
    List<BatchDto> batchDtos = new ArrayList<>();
    BatchDto batchDto = new BatchDto("weihan", "s999999z");
    batchDtos.add(batchDto);

    if (rabbitMqService.isAvailable()) {
      rabbitMqService.sendMessages(batchDtos);
    }

    return new ResponseEntity(batchDto, HttpStatus.OK);
  }
}
