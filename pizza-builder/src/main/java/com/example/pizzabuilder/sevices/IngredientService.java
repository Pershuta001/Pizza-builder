package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.repositories.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Transactional
    public List<Ingredient> getAll(){
        return ingredientRepository.findAll();
    }
    //TODO exception validator
    @Transactional
    public Ingredient save(String name, Boolean vegan, Boolean vegetarian, Boolean spicy) throws Exception{
        if(ingredientRepository.findByName(name).size()>0)
            throw new Exception("e");
        return ingredientRepository.save(Ingredient.builder().name(name).vegan(vegan).spicy(spicy).vegetarian(vegetarian).build());
    }
    /*
    getSpicy getVegeterian getVegan (criteria)
     */
}
