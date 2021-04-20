package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.IngredientInPizzaId;
import com.example.pizzabuilder.model.PizzaPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IngredientsInPizzaRepository extends JpaRepository<IngredientInPizza, IngredientInPizzaId> {
    @Query("SELECT i FROM IngredientInPizza i JOIN FETCH i.ingredient WHERE i.id.patternUuid = ?1")
    List<IngredientInPizza> findByPatternUuid(UUID p);


}
