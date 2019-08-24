package com.govtech.sampleservice.controller;

import com.govtech.sampleservice.service.dtos.SampleDto;
import com.govtech.sampleservice.service.services.SampleService;
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

  @GetMapping(value = "/sampleApi")
  public ResponseEntity sampleApi() {
    SampleDto sampleObj = sampleService.getSampleDto();
    return new ResponseEntity(sampleObj, HttpStatus.OK);
  }
}
