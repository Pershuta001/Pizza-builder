package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.IngredientConvertor;
import com.example.pizzabuilder.convertors.IngredientGroupConvertor;
import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.IngredientGroup;
import com.example.pizzabuilder.repositories.IngredientGroupRepository;
import com.example.pizzabuilder.view.IngredientGroupView;
import com.example.pizzabuilder.view.IngredientView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IngredientGroupService {
    private final IngredientGroupRepository ingredientGroupRepository;
    private final IngredientGroupConvertor ingredientGroupConvertor;
    private final IngredientConvertor ingredientConvertor;
    @Transactional
    public List<IngredientGroup> getAll(){
        return ingredientGroupRepository.findAll();
    }

    @Transactional
    public IngredientGroup findById(UUID uuid) throws Exception{
        Optional<IngredientGroup> ingredientGroup = ingredientGroupRepository.findById(uuid);
        if(!ingredientGroup.isPresent())
            throw new EntityNotExistsException(IngredientGroup.class, uuid);
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

    @Transactional
    public IngredientGroup save(IngredientGroupView view) throws Exception{
        List<IngredientGroup> ingredientGroup = ingredientGroupRepository.findByName(view.getName());
        if(ingredientGroup.size()>0)
            throw new Exception("e");
        return ingredientGroupRepository.save(ingredientGroupConvertor.convert(view));
    }

    //TODO ingredientValidator
    @Transactional
    public IngredientGroup addIngredients(UUID ingredientGroupUUID, List<IngredientView> ingredientViews) throws Exception{
        Optional<IngredientGroup> ingredientGroupOptional = ingredientGroupRepository.findById(ingredientGroupUUID);
        List<Ingredient> ingredients = new ArrayList<>();
        for(IngredientView view:ingredientViews){
            ingredients.add(ingredientConvertor.convert(view));
        }
        if(!ingredientGroupOptional.isPresent())
            throw new EntityNotExistsException(IngredientGroup.class, ingredientGroupUUID);
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
