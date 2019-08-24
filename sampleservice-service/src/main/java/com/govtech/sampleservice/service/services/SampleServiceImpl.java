package com.govtech.sampleservice.service.services;

import com.govtech.sampleservice.repo.entities.Batch;
import com.govtech.sampleservice.repo.repositories.BatchRepository;
import com.govtech.sampleservice.service.dtos.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("hibernateTransactionManager")
@Slf4j
public class SampleServiceImpl implements SampleService {

  @Autowired BatchRepository batchRepository;

  public Batch createBatch() {
    Batch batch = new Batch();
    return batchRepository.save(batch);
  }

  public SampleDto getSampleDto() {
    SampleDto sampleDto = new SampleDto();
    sampleDto.setId(1);
    sampleDto.setRemarks("This is a sample remarks");
    return sampleDto;
  }
}
