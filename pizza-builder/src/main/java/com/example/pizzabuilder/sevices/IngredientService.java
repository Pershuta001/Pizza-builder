package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.IngredientConvertor;
import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.repositories.IngredientGroupRepository;
import com.example.pizzabuilder.repositories.IngredientRepository;
import com.example.pizzabuilder.view.IngredientView;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientConvertor ingredientConvertor;
    private final IngredientGroupRepository ingredientGroupRepository;


    @Transactional
    public List<IngredientView> getAll(){

        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientView> ingredientViews = new ArrayList<>();
        for(Ingredient i : ingredients)
            ingredientViews.add(ingredientConvertor.convert(i));
        return ingredientViews;
    }
    public Ingredient getById(UUID uuid){
        return ingredientRepository.findByUuid(uuid).get();
    }
    @Transactional
    public List<IngredientView> getByGroup(UUID groupUuid){

        List<Ingredient> ingredients = ingredientRepository.findByGroupUuid(ingredientGroupRepository.findByUuid(groupUuid));
        List<IngredientView> ingredientViews = new ArrayList<>();
        for(Ingredient i : ingredients)
            ingredientViews.add(ingredientConvertor.convert(i));
        return ingredientViews;
    }

    @Transactional
    public Ingredient save(String name, Boolean vegan, Boolean vegetarian, Boolean spicy) throws Exception{
        if(ingredientRepository.findByName(name).size()>0)
            throw new Exception("Ingredient with name "+name+ " is exist");
        return ingredientRepository.save(Ingredient.builder().name(name).vegan(vegan).spicy(spicy).vegetarian(vegetarian).build());
    }

    @SneakyThrows
    @Transactional
    public Ingredient save(IngredientView ingredientView){
        if(ingredientRepository.findByName(ingredientView.getName()).size()>0)
            throw new Exception("Ingredient with name "+ingredientView.getName()+ " is exist");
        return ingredientRepository.save(ingredientConvertor.convert(ingredientView));
    }
    /*
    getSpicy getVegeterian getVegan (criteria)
     */
}
