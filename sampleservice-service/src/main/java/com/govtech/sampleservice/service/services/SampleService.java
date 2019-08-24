package com.govtech.sampleservice.service.services;

import com.govtech.sampleservice.repo.entities.Batch;
import com.govtech.sampleservice.service.dtos.SampleDto;

public interface SampleService {

  public Batch createBatch();

  public SampleDto getSampleDto();
}
