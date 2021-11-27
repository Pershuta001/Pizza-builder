package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.IngredientGroup;
import com.example.pizzabuilder.view.IngredientGroupView;
import com.example.pizzabuilder.view.IngredientView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IngredientGroupConvertor {

    public IngredientGroup convert(IngredientGroupView ingredientGroupView){
        return IngredientGroup.builder()
                .name(ingredientGroupView.getName())
                .uuid(ingredientGroupView.getUuid())
                .label(ingredientGroupView.getLabel())
                .build();
    }
    @SneakyThrows
    public IngredientGroupView convert(IngredientGroup ingredientGroup) {

        return IngredientGroupView.builder()
                .label(ingredientGroup.getLabel())
                .name(ingredientGroup.getName())
                .uuid(ingredientGroup.getUuid())
                .build();

    }
}
