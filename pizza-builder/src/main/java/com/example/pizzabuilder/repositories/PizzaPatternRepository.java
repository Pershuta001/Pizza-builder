package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.PizzaPattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PizzaPatternRepository extends JpaRepository<PizzaPattern, UUID> {
}
