package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.IngredientGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    List<Ingredient> findByName(String name);
    List<Ingredient> findByGroupUuid(IngredientGroup groupUuid);


}
