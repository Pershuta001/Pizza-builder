package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.IngredientInPizzaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsInPizzaRepository extends JpaRepository<IngredientInPizza, IngredientInPizzaId> {

}
