package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.IngredientInPizzaId;
import com.example.pizzabuilder.view.IngredientInPizzaView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientInPizzaConvertor {
    private final IngredientConvertor ingredientConvertor;
    @SneakyThrows
    public IngredientInPizza convert(IngredientInPizzaView ingredientView) {

        return IngredientInPizza.builder()
                .ingredient(ingredientConvertor.convert(ingredientView.getIngredients()))
                .quantity(ingredientView.getQuantity())
                .id(IngredientInPizzaId.builder()
                        .patternUuid(ingredientView.getPatternUuid())
                        .ingredientUuid(ingredientView.getIngredients().getUuid())
                        .build()
                )
                .build();

    }
}
