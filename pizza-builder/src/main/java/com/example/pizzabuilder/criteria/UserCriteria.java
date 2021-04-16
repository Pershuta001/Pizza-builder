package com.example.pizzabuilder.criteria;


import com.example.pizzabuilder.exceptions.WrongRestrictionException;
import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.utils.Utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserCriteria extends Criteria<UserEntity> {
    private List<UUID> ids;
    private String name;
    private String email;

    public UserCriteria() throws WrongRestrictionException {
        this(0, 0, null);
    }

    public UserCriteria(String restriction) throws WrongRestrictionException {
        this(0, 0, restriction);
    }

    public UserCriteria(int offset, int limit, String restriction) throws WrongRestrictionException {
        super(offset, limit, UserEntity.class);
        UserCriteria parsed = parse(restriction, UserCriteria.class);
        if (parsed != null) {
            this.ids = parsed.ids;
            this.name = parsed.name;
            this.email = parsed.email;
        }
    }

    @Override
    public List<Predicate> query(Root<UserEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<UUID> expression = root.get("uuid");
            predicates.add(expression.in(this.ids));
        }

        if (Utils.notEmpty(this.name)) {
            Expression<String> expression = root.get("name");
            predicates.add(cb.like(expression, this.name));
        }

        if (Utils.notEmpty(this.email)) {
            Expression<String> expression = root.get("email");
            predicates.add(cb.like(expression, this.email));
        }

        return predicates;
    }

}

