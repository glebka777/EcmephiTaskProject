package ru.ecmephi.user.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

abstract class BaseDAO<T, PK extends Serializable> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager entityManager;

    public abstract void create(T entity);

    public abstract List<T> getAll();

    public abstract T getById(PK id);

    public abstract T remove(PK id);

    public abstract void update(T entity);

    T remove(Class<T> entityClass, PK primaryKey) {
        T instance = entityManager.find(entityClass, primaryKey);
        if (instance != null) {
            entityManager.remove(instance);
            return instance;
        }
        return null;
    }

    List<T> getAll(Class<T> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

}
