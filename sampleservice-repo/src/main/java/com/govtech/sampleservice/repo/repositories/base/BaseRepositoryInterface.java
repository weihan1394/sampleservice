package com.govtech.sampleservice.repo.repositories.base;

import com.govtech.sampleservice.repo.entities.EntityInterface;
import java.util.List;

public interface BaseRepositoryInterface<T extends EntityInterface>
    extends BaseReadOnlyRepositoryInterface {

  List<T> saveAll(List<T> list);

  T save(T item);

  void flush();

  void clear();

  void update(T entity);
}
