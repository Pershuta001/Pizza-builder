package com.example.pizzabuilder.criteria;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class Criteria<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public Criteria(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    public List<T> getAll(){
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(entityClass);
            Root<T> root = cr.from(entityClass);
            cr.select(root);
            Query query = entityManager.createQuery(cr);
            return query.getResultList();
    }




}
