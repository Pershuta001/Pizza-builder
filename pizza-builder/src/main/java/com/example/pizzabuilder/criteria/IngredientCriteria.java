package com.example.pizzabuilder.criteria;

import com.example.pizzabuilder.exceptions.WrongRestrictionException;
import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.User;
import com.example.pizzabuilder.utils.Utils;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IngredientCriteria extends Criteria<Ingredient> {
    private List<Integer> ids;
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Boolean spicy;
    private Boolean vegetarian;
    private Boolean vegan;

    public IngredientCriteria() throws WrongRestrictionException {
        this(0, 0, null);
    }

    public IngredientCriteria(String restriction) throws WrongRestrictionException {
        this(0, 0, restriction);
    }

    public IngredientCriteria(int offset, int limit, String restriction) throws WrongRestrictionException {
        super(offset, limit, Ingredient.class);
        IngredientCriteria parsed = parse(restriction, IngredientCriteria.class);
        if (parsed != null) {
            this.ids = parsed.ids;
            this.name = parsed.name;
            this.minPrice = parsed.minPrice;
            this.maxPrice = parsed.maxPrice;
            this.spicy=parsed.spicy;
            this.vegan=parsed.vegan;
            this.vegetarian=parsed.vegetarian;
        }
    }

    @Override
    public List<Predicate> query(Root<Ingredient> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<UUID> expression = root.get("uuid");
            predicates.add(expression.in(this.ids));
        }

        if (Utils.notEmpty(this.name)) {
            Expression<String> expression = root.get("name");
            predicates.add(cb.like(expression, this.name));
        }

        if (this.spicy !=null) {
            Expression<Boolean> expression = root.get("spicy");
            predicates.add(cb.equal(expression, this.spicy));
        }
        if (this.vegetarian !=null) {
            Expression<Boolean> expression = root.get("vegetarian");
            predicates.add(cb.equal(expression, this.vegetarian));
        }
        if (this.vegan !=null) {
            Expression<Boolean> expression = root.get("vegan");
            predicates.add(cb.equal(expression, this.vegan));
        }
        if (this.maxPrice !=null) {
            Expression<Double> expression = root.get("price");
            predicates.add(cb.lessThanOrEqualTo(expression, this.maxPrice));
        }
        if (this.minPrice !=null) {
            Expression<Double> expression = root.get("price");
            predicates.add(cb.greaterThanOrEqualTo(expression, this.minPrice));
        }

        return predicates;
    }


}

