package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.IngredientInPizzaId;
import com.example.pizzabuilder.repositories.IngredientsInPizzaRepository;
import com.example.pizzabuilder.repositories.PizzaPatternRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IngredientInPizzaService {
    private final IngredientsInPizzaRepository ingredientsInPizzaRepository;
    @Transactional
    public IngredientInPizza getByID(IngredientInPizzaId id) throws Exception{
        Optional<IngredientInPizza> ingredientInPizzaOptional = ingredientsInPizzaRepository.findById(id);
        if(!ingredientInPizzaOptional.isPresent())
            throw new Exception("No ingredient in pizza with id "+ id.toString());
        return ingredientInPizzaOptional.get();
    }
    //TODO exception
    @Transactional
    public IngredientInPizza setQuantity(IngredientInPizzaId ingredientInPizzaId, Integer quantity) throws Exception{
        Optional<IngredientInPizza> ingredientInPizzaOptional = ingredientsInPizzaRepository.findById(ingredientInPizzaId);
        if(!ingredientInPizzaOptional.isPresent())
            throw new Exception("No ingredient in pizza with id "+ ingredientInPizzaId.toString());
        IngredientInPizza ingredientInPizza = ingredientInPizzaOptional.get();
        ingredientInPizza.setQuantity(quantity);
        return ingredientsInPizzaRepository.saveAndFlush(ingredientInPizza);
    }
    @Transactional
    public List<IngredientInPizza> getByPizzaPattern(UUID pizzaPatternUuid) throws Exception{
        return ingredientsInPizzaRepository.findByPatternUuid(pizzaPatternUuid);
    }


}
