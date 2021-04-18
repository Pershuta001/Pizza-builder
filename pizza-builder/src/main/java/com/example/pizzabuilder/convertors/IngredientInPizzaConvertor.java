package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.IngredientInPizzaId;
import com.example.pizzabuilder.sevices.IngredientService;
import com.example.pizzabuilder.view.IngredientInPizzaFullView;
import com.example.pizzabuilder.view.IngredientInPizzaView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientInPizzaConvertor {
    private final IngredientConvertor ingredientConvertor;
    private final IngredientService ingredientService;
    @SneakyThrows
    public IngredientInPizza convert(IngredientInPizzaView ingredientView) {

        return IngredientInPizza.builder()
                .quantity(ingredientView.getQuantity())
                .id(IngredientInPizzaId.builder()
                        .ingredientUuid(ingredientView.getIngredientUuid())
                        .build()
                )
                .build();

    }
    @SneakyThrows
    public IngredientInPizzaView convert(IngredientInPizza ingredientView) {

        return IngredientInPizzaView.builder()
                .ingredientUuid(ingredientView.getId().getIngredientUuid())
                .quantity(ingredientView.getQuantity())
                .build();

    }

    public IngredientInPizzaFullView convertFull(IngredientInPizza ingredient) {
        return IngredientInPizzaFullView.builder()
                .ingredientUuid(ingredient.getId().getIngredientUuid())
                .quantity(ingredient.getQuantity())
                .ingredient(ingredientConvertor.convert(ingredientService.getById(ingredient.getId().getIngredientUuid())))
                .build();
    }
}
