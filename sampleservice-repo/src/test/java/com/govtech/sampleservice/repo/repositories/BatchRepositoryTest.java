package com.govtech.sampleservice.repo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.govtech.sampleservice.repo.config.TestHibernateConf;
import com.govtech.sampleservice.repo.entities.Batch;
import com.govtech.sampleservice.repo.factories.BatchFactory;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional("hibernateTransactionManager")
@ContextConfiguration(
    classes = {
      TestHibernateConf.class,
      BatchRepositoryImpl.class,
    },
    loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BatchRepositoryTest {
  @Autowired private BatchRepository batchRepository;

  @Test
  public void saveWithValidBatch() {
    Batch batch = BatchFactory.build();
    batchRepository.save(batch);
    assertThat(batch.getId()).isNotNull();
  }

  @Test
  public void saveAllWithValidBatches() {
    List<Batch> batches = BatchFactory.build(2);
    batchRepository.saveAll(batches);
    assertThat(batches.get(0).getId()).isNotNull();
    assertThat(batches.get(1).getId()).isNotNull();
  }
}
