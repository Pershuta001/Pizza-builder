package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.IngredientGroup;
import com.example.pizzabuilder.repositories.IngredientGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IngredientGroupService {
    private final IngredientGroupRepository ingredientGroupRepository;
    @Transactional
    public List<IngredientGroup> getAll(){
        return ingredientGroupRepository.findAll();
    }

    @Transactional
    public IngredientGroup findById(UUID uuid) throws Exception{
        Optional<IngredientGroup> ingredientGroup = ingredientGroupRepository.findById(uuid);
        if(!ingredientGroup.isPresent())
            throw new Exception("e");
        return ingredientGroup.get();
    }

    //TODO validator for saving
    @Transactional
    public IngredientGroup save(String name, List<Ingredient> ingredients) throws Exception{
        List<IngredientGroup> ingredientGroup = ingredientGroupRepository.findByName(name);
        if(ingredientGroup.size()>0)
            throw new Exception("e");
        return ingredientGroupRepository.save(IngredientGroup.builder().name(name).ingredients(ingredients).build());
    }
    //TODO ingredientValidator
    @Transactional
    public IngredientGroup addIngredients(UUID ingredientGroupUUID, List<Ingredient> ingredients) throws Exception{
        Optional<IngredientGroup> ingredientGroupOptional = ingredientGroupRepository.findById(ingredientGroupUUID);
        if(!ingredientGroupOptional.isPresent())
            throw new Exception("e");
        for (Ingredient ingredient:ingredients) {
            if(ingredient.getGroupUuid()!=null)
                throw new Exception("e");
        }
        IngredientGroup ingredientGroup = ingredientGroupOptional.get();
        List<Ingredient> ingredients1= ingredientGroup.getIngredients();
        ingredients1.addAll(ingredients);
        ingredientGroup.setIngredients(ingredients1);
        return ingredientGroupRepository.saveAndFlush(ingredientGroup);
    }

}
