package com.example.pizzabuilder.repositories.criteria;

import com.example.pizzabuilder.criteria.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CriteriaRepositiry implements ICriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> List<T> find(Criteria<T> criteria) {
        Query query = criteria.createQuery(entityManager);
        if (criteria.getOffset() > 0)
            query.setFirstResult(criteria.getOffset());
        if (criteria.getLimit() > 0)
            query.setMaxResults(criteria.getLimit());
        return query.getResultList();
    }

    @Override
    public <T> long count(Criteria<T> criteria) {
        Query query = criteria.createCountQuery(entityManager);
        return (Long) query.getSingleResult();
    }

    @Override
    public <T> Long countLong(Criteria<T> criteria) {
        Query query = criteria.createCountQuery(entityManager);
        return (Long) query.getSingleResult();
    }
}
