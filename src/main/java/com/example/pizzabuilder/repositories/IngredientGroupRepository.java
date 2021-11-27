package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.IngredientGroup;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientGroupRepository extends JpaRepository<IngredientGroup, UUID> {
    List<IngredientGroup> findByName(String name);

    IngredientGroup findByUuid(UUID uuid);
}
