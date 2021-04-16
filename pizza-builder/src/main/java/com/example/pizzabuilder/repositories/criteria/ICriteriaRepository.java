package com.example.pizzabuilder.repositories.criteria;

import com.example.pizzabuilder.criteria.Criteria;

import java.util.List;

public interface ICriteriaRepository  {
    <T> List<T> find(Criteria<T> criteria);

    <T> long count(Criteria<T> criteria);

    <T> Long countLong(Criteria<T> criteria);
}
