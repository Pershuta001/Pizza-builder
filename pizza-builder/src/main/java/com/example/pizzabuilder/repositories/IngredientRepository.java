package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.IngredientGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientRepository extends JpaRepository<IngredientGroup, UUID> {
}
