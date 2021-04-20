package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.PizzaInOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PizzaInOrderRepository extends JpaRepository<PizzaInOrder, PizzaInOrderId> {

    @Query(value = "SELECT * " +
            "FROM pizzabuilder.public.pizza_in_order p " +
            "WHERE p.orders_uuid IN ( " +
            "   SELECT o.uuid " +
            "   From pizzabuilder.public.orders o " +
            "   where o.user_uuid in ( " +
            "         Select u.uuid " +
            "         from pizzabuilder.public.users u " +
            "         WHERE u.email = ?1"+
            "" +
            ") AND o.status = 'IN_CART'" +
            ")",
            nativeQuery = true
    )
    List<PizzaInOrder> getCartByUserEmail(String email);

}
