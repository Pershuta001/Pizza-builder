package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.IngredientGroup;
import com.example.pizzabuilder.view.IngredientGroupView;
import org.springframework.stereotype.Component;

@Component
public class IngredientGroupConvertor {

    public IngredientGroup convert(IngredientGroupView ingredientGroupView){
        return IngredientGroup.builder()
                .name(ingredientGroupView.getName())
                .uuid(ingredientGroupView.getUuid())
                .build();
    }

}
