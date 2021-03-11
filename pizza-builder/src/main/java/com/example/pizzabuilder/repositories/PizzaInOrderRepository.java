package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.PizzaInOrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaInOrderRepository extends JpaRepository<PizzaInOrder, PizzaInOrderId> {
}
