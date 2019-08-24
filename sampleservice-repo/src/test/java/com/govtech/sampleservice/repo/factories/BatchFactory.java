package com.govtech.sampleservice.repo.factories;

import com.github.javafaker.Faker;
import com.govtech.sampleservice.repo.entities.Batch;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BatchFactory {
  private static Faker faker = new Faker();

  public static Batch build() {
    Batch batch = new Batch();
    batch.setEntered(LocalDateTime.now());
    batch.setRemarks(faker.lorem().sentence());
    return batch;
  }

  public static List<Batch> build(int size) {
    List<Batch> batches = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      batches.add(build());
    }
    return batches;
  }
}
