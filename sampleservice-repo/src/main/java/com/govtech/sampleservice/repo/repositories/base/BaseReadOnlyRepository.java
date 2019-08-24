package com.govtech.sampleservice.repo.repositories.base;

import com.govtech.sampleservice.repo.entities.EntityInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseReadOnlyRepository<T extends EntityInterface> {

  @PersistenceContext private EntityManager em;

  @Autowired SessionFactory sessionFactory;

  @Value("${config.pageSize}")
  Integer pageSize;

  public List<T> findAll(Class<T> clazz) {
    CriteriaBuilder cb = em.getCriteriaBuilder();

    CriteriaQuery<T> cq = cb.createQuery(clazz);
    cq.from(clazz);
    TypedQuery<T> query = em.createQuery(cq);
    return query.getResultList();
  }

  public T findById(Class<T> clazz, Integer id) {
    return em.find(clazz, id);
  }

  public List<T> findAllNonSuperseded(Class<T> clazz) {
    CriteriaBuilder cb = em.getCriteriaBuilder();

    CriteriaQuery<T> cq = cb.createQuery(clazz);
    Root root = cq.from(clazz);
    cq.where(cb.isNull(root.get("temporalData").get("superseded")));
    TypedQuery<T> query = em.createQuery(cq);
    return query.getResultList();
  }

  public List<T> findAllNonSuperseded(Class<T> clazz, int page) {
    StatelessSession statelessSession = sessionFactory.openStatelessSession();
    long offset = (long) page * pageSize;
    String query = "SELECT c FROM " + clazz.getName() + " c WHERE c.temporalData.superseded = NULL";
    List resultList =
        statelessSession
            .createQuery(query)
            .setReadOnly(true)
            .setCacheable(false)
            .setFirstResult((int) offset)
            .setMaxResults(pageSize)
            .getResultList();
    statelessSession.close();
    return resultList;
  }

  // Commented out scrolling function for future use - Don't delete unless you're sure
  // public ScrollableResults findAllNonSupersededReadOnly(Class<T> clazz) {
  //   StatelessSession statelessSession =
  //       em.unwrap(Session.class).getSessionFactory().openStatelessSession();
  //   String query = "SELECT c FROM " + clazz.getName() + " c WHERE c.temporalData.superseded =
  // NULL";
  //   ScrollableResults scrollableResults =
  //   return statelessSession
  //       .createQuery(query)
  //       .setReadOnly(true)
  //       .setCacheable(false)
  //       .scroll(ScrollMode.FORWARD_ONLY);
  //   statelessSession.close();
  //   return scrollableResults;
  // }
}
