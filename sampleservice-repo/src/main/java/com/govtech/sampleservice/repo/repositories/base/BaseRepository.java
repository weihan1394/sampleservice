package com.govtech.sampleservice.repo.repositories.base;

import com.govtech.sampleservice.repo.entities.EntityInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional("hibernateTransactionManager")
public abstract class BaseRepository<T extends EntityInterface> extends BaseReadOnlyRepository {

  @PersistenceContext private EntityManager em;

  public List<T> saveAll(List<T> list) {
    for (T e : list) {
      em.persist(e);
    }
    return list;
  }

  public T save(T item) {
    em.persist(item);
    return item;
  }

  public void flush() {
    em.flush();
  }

  public void clear() {
    em.clear();
  }

  public void update(T entity) {
    em.merge(entity);
  }
}
