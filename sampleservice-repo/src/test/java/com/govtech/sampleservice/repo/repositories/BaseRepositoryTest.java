package com.govtech.sampleservice.repo.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.govtech.sampleservice.repo.config.TestHibernateConf;
import com.govtech.sampleservice.repo.entities.Batch;
import com.govtech.sampleservice.repo.factories.BatchFactory;
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
public class BaseRepositoryTest {

  @Autowired private BatchRepository batchRepository;

  @Test
  public void GivenValidBatch_WhenSaveWithFlush_ThenCorrectResult() {
    Batch batch = BatchFactory.build();
    batchRepository.save(batch);
    batchRepository.flush();
    assertThat(batch.getId()).isNotNull();
  }

  @Test
  public void GivenValidBatch_WhenUpdate_ThenCorrectResult() {
    Batch batch = BatchFactory.build();
    batchRepository.save(batch);
    batchRepository.flush();

    batch.setRemarks("This is an updated remarks");
    batchRepository.update(batch);

    Batch batchRetrieved = (Batch) batchRepository.findById(Batch.class, batch.getId());
    assertEquals("This is an updated remarks", batchRetrieved.getRemarks());
  }
}
