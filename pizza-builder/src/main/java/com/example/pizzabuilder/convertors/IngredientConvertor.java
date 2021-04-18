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

        IngredientGroup group =  ingredientGroupService.getByUUID(ingredientView.getGroupUuid());

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
    @SneakyThrows
    public IngredientView convert(Ingredient ingredient) {

        return IngredientView.builder()
                .name(ingredient.getName())
                .spicy(ingredient.getSpicy())
                .vegetarian(ingredient.getVegetarian())
                .vegan(ingredient.getVegan())
                .price(ingredient.getPrice())
                .photoUrl(ingredient.getPhotoUrl())
                .uuid(ingredient.getUuid())
                .groupUuid(ingredient.getGroupUuid().getUuid())
                .groupName(ingredient.getGroupUuid().getName())
                .build();

    }
}
