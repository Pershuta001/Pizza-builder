package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.IngredientGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    List<Ingredient> findByName(String name);

    List<Ingredient> findByGroupUuid(IngredientGroup groupUuid);

    Optional<Ingredient> findByUuid(UUID uuid);

    @Query(value = "select * from pizzabuilder.public.ingredient i where i.uuid in (" +
            "select ip.ingredient_uuid" +
            " from ingredient_in_pizza ip " +
            "where pattern_uuid = ?1)", nativeQuery = true)
    List<Ingredient> getByPatternUUID(UUID patternUUID);

}
