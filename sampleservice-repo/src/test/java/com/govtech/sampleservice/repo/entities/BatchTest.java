package com.govtech.sampleservice.repo.entities;

import static org.assertj.core.api.Assertions.assertThat;

import com.govtech.sampleservice.repo.factories.BatchFactory;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BatchTest {

  private Validator validator;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void validBatch() {
    Batch batch = BatchFactory.build();
    assertThat(validator.validate(batch).isEmpty()).isTrue();
  }

  @Test
  public void invalidBatchWithEnteredNull() {
    Batch batch = BatchFactory.build();
    batch.setEntered(null);
    assertThat(validator.validate(batch).isEmpty()).isFalse();
  }

  @Test
  public void batchWithValidToString() {
    Batch batch = BatchFactory.build();
    assertThat(batch.toString()).isNotEmpty();
  }
}
