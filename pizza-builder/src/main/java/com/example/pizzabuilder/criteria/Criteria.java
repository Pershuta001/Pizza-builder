package com.example.pizzabuilder.criteria;


import com.example.pizzabuilder.enums.OrderDirectionEnum;
import com.example.pizzabuilder.exceptions.WrongRestrictionException;
import com.example.pizzabuilder.utils.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Getter
@Setter
public abstract class Criteria<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<T> getAll(){
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(entityClass);
            Root<T> root = cr.from(entityClass);
            cr.select(root);
            Query query = entityManager.createQuery(cr);
            return query.getResultList();
    }
    private int offset;
    private int limit;
    private String order_by = "uuid";
    private OrderDirectionEnum order_direction = OrderDirectionEnum.ASC;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String userCriteria;
    @Getter(AccessLevel.NONE)
    private final Class<T> entityClass;

    public Criteria(Class<T> entityClass){
        this.entityClass = entityClass;
    }
    public Criteria(int offset, int limit, Class<T> entityClass) {
        this.offset = offset;
        this.limit = limit;
        this.entityClass = entityClass;
    }

    /**
     * implement this to set query params
     *
     * @param root - entity root.
     *             use <code>root.get("field_name")</code> to get the field from root entity
     * @param cb   - create main query expressions
     * @return list of predicates to be applied to query
     */
    public abstract List<Predicate> query(Root<T> root, CriteriaBuilder cb);

    public Query createQuery(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);

        Root<T> root = query.from(entityClass);
        query.select(root);
        query.distinct(true);
        List<Predicate> predicates = query(root, cb);
        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        query.orderBy(formOrder(cb, root));

        return em.createQuery(query);
    }

    public Query createCountQuery(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<T> root = query.from(entityClass);
        query.select(cb.count(root));
        query.distinct(true);
        List<Predicate> predicates = query(root, cb);
        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        return em.createQuery(query);
    }

    private Order formOrder(CriteriaBuilder cb, Root<T> root) {
        Order order;

        if (order_by != null && !order_by.isEmpty()) {
            if (order_direction != null && order_direction == OrderDirectionEnum.DESC) {
                order = cb.desc(root.get(order_by));
            } else {
                order = cb.asc(root.get(order_by));
            }
        } else {
            order = cb.asc(root.get("id"));
        }

        return order;
    }

    /**
     * sets limit, offset, orderBy, OrderDirection by parsing the json string
     * json string is cached to the userCriteria
     * return null if {#restriction} is empty or null
     *
     * @param restriction - json representation of object
     * @param clazz       - class of object
     * @param <V>         - type that extends Criteria
     * @return - object of type <V>
     * @throws WrongRestrictionException - if passed wrong json format
     */
    protected <V extends Criteria<?>> V parse(String restriction, Class<V> clazz) throws WrongRestrictionException {
        if (restriction == null || restriction.isEmpty() || restriction.equals("{}"))
            return null;

        userCriteria = restriction;

        try {
            V parsed = Utils.objectMapper.readValue(restriction, clazz);

            if (parsed.getLimit() > 0) {
                setLimit(parsed.getLimit());
            }

            if (parsed.getOffset() > 0) {
                setOffset(parsed.getOffset());
            }

            setOrder_by(parsed.getOrder_by());
            setOrder_direction(parsed.getOrder_direction());

            return parsed;
        } catch (Exception e) {
            throw new WrongRestrictionException();
        }
    }


    @Override
    public String toString() {
        if (userCriteria != null) return userCriteria;
        else return "Criteria";
    }
}
