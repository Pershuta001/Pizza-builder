package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.IngredientGroup;
import com.example.pizzabuilder.sevices.IngredientGroupService;
import com.example.pizzabuilder.view.IngredientView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IngredientConvertor {

    private final IngredientGroupService ingredientGroupService;

    @SneakyThrows
    public Ingredient convert(IngredientView ingredientView) {

        IngredientGroup group =  ingredientGroupService.getByUUID(ingredientView.getUuid());

        return Ingredient.builder()
                .name(ingredientView.getName())
                .spicy(ingredientView.getSpicy())
                .vegetarian(ingredientView.getVegetarian())
                .vegan(ingredientView.getVegan())
                .price(ingredientView.getPrice())
                .photoUrl(ingredientView.getPhotoUrl())
                .uuid(ingredientView.getUuid())
                .groupUuid(group)
                .build();

    }
}
