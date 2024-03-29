package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.enums.OrderStatusEnum;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByStatus(OrderStatusEnum status);

    List<Order> findByStatusIsNotLike(OrderStatusEnum status);


    List<Order> findByStatusAndUserEntity(OrderStatusEnum status, UserEntity userEntity);

    @Query(value = "select * " +
            "from orders " +
            "where status = 'IN_CART' AND user_uuid = ?1 " +
            "", nativeQuery = true)
    Optional<Order> getCart(UserEntity userEntity);

    Optional<Order> findById(Integer uuid);

    void deleteById(Integer id);


}
