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

    @Transactional
    public List<IngredientGroupView> getAll(){
        List<IngredientGroup> ingredientGroups = ingredientGroupRepository.findAll();
        List<IngredientGroupView> ingredientGroupViews = new ArrayList<>();
        for(IngredientGroup i : ingredientGroups)
            ingredientGroupViews.add(ingredientGroupConvertor.convert(i));
        return ingredientGroupViews;
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
            throw new Exception("Ingredient group with name  "+ name + " is exist");
        return ingredientGroupRepository.save(IngredientGroup.builder().name(name).ingredients(ingredients).build());
    }

    @Transactional
    public IngredientGroup save(IngredientGroupView view) throws Exception{
        List<IngredientGroup> ingredientGroup = ingredientGroupRepository.findByName(view.getName());
        if(ingredientGroup.size()>0)
            throw new Exception("Ingredient group with name  "+ view.getName() + " is exist");
        return ingredientGroupRepository.save(ingredientGroupConvertor.convert(view));
    }

    @Transactional
    public IngredientGroup addIngredients(UUID ingredientGroupUUID, List<Ingredient> ingredients) throws Exception{
        Optional<IngredientGroup> ingredientGroupOptional = ingredientGroupRepository.findById(ingredientGroupUUID);
        if(!ingredientGroupOptional.isPresent())
            throw new EntityNotExistsException(IngredientGroup.class, ingredientGroupUUID);
        for (Ingredient ingredient:ingredients) {
            if(ingredient.getGroupUuid()!=null)
                throw new Exception("Ingredient "+ingredient.getName()+" in another group");
        }
        IngredientGroup ingredientGroup = ingredientGroupOptional.get();
        List<Ingredient> ingredients1= ingredientGroup.getIngredients();
        ingredients1.addAll(ingredients);
        ingredientGroup.setIngredients(ingredients1);
        return ingredientGroupRepository.saveAndFlush(ingredientGroup);
    }
    @Transactional
    public IngredientGroup addIngredient(UUID ingredientGroupUUID, Ingredient ingredient) throws Exception{
        Optional<IngredientGroup> ingredientGroupOptional = ingredientGroupRepository.findById(ingredientGroupUUID);
        if(!ingredientGroupOptional.isPresent())
            throw new EntityNotExistsException(IngredientGroup.class, ingredientGroupUUID);

        if(ingredient.getGroupUuid()!=null&&ingredient.getGroupUuid().getUuid()!=ingredientGroupUUID)
            throw new Exception("Ingredient "+ingredient.getName()+" in another group");

        IngredientGroup ingredientGroup = ingredientGroupOptional.get();
        ingredientGroup.getIngredients().add(ingredient);
        return ingredientGroupRepository.saveAndFlush(ingredientGroup);
    }

    @Transactional
    public IngredientGroup getByUUID(UUID uuid){
        return ingredientGroupRepository.findByUuid(uuid);
    }
    @Transactional
    public void delete(UUID uuid) {
        ingredientGroupRepository.deleteById(uuid);
    }
}
