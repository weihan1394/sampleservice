package com.govtech.sampleservice.service.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.govtech.sampleservice.repo.entities.Batch;
import com.govtech.sampleservice.repo.factories.BatchFactory;
import com.govtech.sampleservice.repo.repositories.BatchRepository;
import com.govtech.sampleservice.repo.repositories.BatchRepositoryImpl;
import com.govtech.sampleservice.service.dtos.SampleDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = {
      SampleServiceImpl.class,
      BatchRepositoryImpl.class,
    },
    loader = AnnotationConfigContextLoader.class)
public class SampleServiceTest {

  @Autowired private SampleService sampleService;
  @MockBean private BatchRepository batchRepository;

  private Batch batch;

  @Before
  public void setUp() {
    batch = BatchFactory.build();
    batch.setId(1);
    when(batchRepository.save(any(Batch.class))).thenReturn(batch);
  }

  @Test
  public void createBatchTest() {
    Batch batch = sampleService.createBatch();
    assertEquals(1, batch.getId());
  }

  @Test
  public void getSampleDtoTest() {
    SampleDto sampleDto = sampleService.getSampleDto();
    assertEquals(1, sampleDto.getId());
    assertEquals("This is a sample remarks", sampleDto.getRemarks());
  }
}
