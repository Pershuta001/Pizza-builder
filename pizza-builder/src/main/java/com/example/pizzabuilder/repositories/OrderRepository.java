package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
