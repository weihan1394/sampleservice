package com.govtech.sampleservice.repo.repositories.base;

import com.govtech.sampleservice.repo.entities.EntityInterface;
import java.util.List;

public interface BaseReadOnlyRepositoryInterface<T extends EntityInterface> {

  List<T> findAll(Class<T> clazz);

  List<T> findAllNonSuperseded(Class<T> clazz);

  T findById(Class<T> clazz, Integer id);

  // Commented out for future use
  // ScrollableResults findAllNonSupersededReadOnly(Class<T> clazz);
}
