package com.example.pizzabuilder.criteria;

import com.example.pizzabuilder.exceptions.WrongRestrictionException;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.PizzaInOrder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderCriteria extends Criteria<Order>{

    /*
    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = false)
    private User user;

    @OneToMany
    private List<PizzaInOrder> pizzaInOrders;
     */
    private List<Integer> ids;
    private Double minPrice;
    private Double maxPrice;
    private Integer status;
    private Date dateFrom;
    private Date dateTo;
    private UUID user;
    private List<PizzaInOrder> pizzaInOrders;

    public OrderCriteria() throws WrongRestrictionException {
        this(0, 0, null);
    }

    public OrderCriteria(String restriction) throws WrongRestrictionException {
        this(0, 0, restriction);
    }

    public OrderCriteria(int offset, int limit, String restriction) throws WrongRestrictionException {
        super(offset, limit, Order.class);
        OrderCriteria parsed = parse(restriction, OrderCriteria.class);
        if (parsed != null) {
            this.ids = parsed.ids;
            this.minPrice = parsed.minPrice;
            this.maxPrice = parsed.maxPrice;
            this.status = parsed.status;
            this.dateFrom = parsed.dateFrom;
            this.dateTo = parsed.dateTo;
            this.user=parsed.user;
            pizzaInOrders = parsed.pizzaInOrders;
        }
    }

    @Override
    public List<Predicate> query(Root<Order> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<UUID> expression = root.get("uuid");
            predicates.add(expression.in(this.ids));
        }
        if(status!=null){
            Expression<Integer> expression = root.get("status");
            predicates.add(cb.equal(expression, this.status));
        }
        if (this.maxPrice !=null) {
            Expression<Double> expression = root.get("total_price");
            predicates.add(cb.lessThanOrEqualTo(expression, this.maxPrice));
        }
        if (this.minPrice !=null) {
            Expression<Double> expression = root.get("total_price");
            predicates.add(cb.greaterThanOrEqualTo(expression, this.minPrice));
        }
        if (this.dateFrom !=null) {
            Expression<Date> expression = root.get("date_time");
            predicates.add(cb.greaterThanOrEqualTo(expression, this.dateFrom));
        }
        if (this.dateTo !=null) {
            Expression<Date> expression = root.get("date_time");
            predicates.add(cb.lessThanOrEqualTo(expression, this.dateTo));
        }
        //TODO by user, by pizza???

        return predicates;
    }

}
