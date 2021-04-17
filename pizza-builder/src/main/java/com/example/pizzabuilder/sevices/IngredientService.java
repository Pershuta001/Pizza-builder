package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.IngredientConvertor;
import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.repositories.IngredientRepository;
import com.example.pizzabuilder.view.IngredientView;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientConvertor ingredientConvertor;

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

    @SneakyThrows
    @Transactional
    public Ingredient save(IngredientView ingredientView){
        if(ingredientRepository.findByName(ingredientView.getName()).size()>0)
            throw new Exception("e");
        return ingredientRepository.save(ingredientConvertor.convert(ingredientView));
    }
    /*
    getSpicy getVegeterian getVegan (criteria)
     */
}
