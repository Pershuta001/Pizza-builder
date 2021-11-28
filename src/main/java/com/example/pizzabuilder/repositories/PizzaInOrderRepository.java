package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.PizzaInOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PizzaInOrderRepository extends JpaRepository<PizzaInOrder, PizzaInOrderId> {

    @Query(value = "SELECT * " +
            "FROM pizza_in_order p " +
            "WHERE p.orders_uuid IN ( " +
            "   SELECT o.uuid " +
            "   From orders o " +
            "   where o.user_uuid in ( " +
            "         Select u.uuid " +
            "         from users u " +
            "         WHERE u.email = ?1"+
            "" +
            ") AND o.status = 'IN_CART'" +
            ")",
            nativeQuery = true
    )
    List<PizzaInOrder> getCartByUserEmail(String email);

    @Query(
            value = "select * " +
                    "from pizza_in_order " +
                    "WHERE pizza_pattern_uuid = ?1 AND pizza_size = ?2",nativeQuery = true
    )
    List<PizzaInOrder> findAllByIdAndSize(UUID pattern, Integer size);


}
