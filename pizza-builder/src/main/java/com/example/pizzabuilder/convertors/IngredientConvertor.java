package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.sevices.IngredientGroupService;
import com.example.pizzabuilder.view.IngredientView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientConvertor {

    private final IngredientGroupService ingredientGroupService;

    @SneakyThrows
    public Ingredient convert(IngredientView ingredientView) {
        return Ingredient.builder()
                .name(ingredientView.getName())
                .spicy(ingredientView.getSpicy())
                .vegetarian(ingredientView.getVegetarian())
                .price(ingredientView.getPrice())
                .photo(ingredientView.getPhoto())
                .uuid(ingredientView.getUuid())
                .build();

    }
}
