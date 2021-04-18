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
                .ingredient(ingredientConvertor.convert(ingredientView.getIngredient()))
                .quantity(ingredientView.getQuantity())
                .id(IngredientInPizzaId.builder()
                        .patternUuid(ingredientView.getPatternUuid())
                        .ingredientUuid(ingredientView.getIngredient().getUuid())
                        .build()
                )
                .build();

    }
    @SneakyThrows
    public IngredientInPizzaView convert(IngredientInPizza ingredientView) {

        return IngredientInPizzaView.builder()
                .ingredient(ingredientConvertor.convert(ingredientView.getIngredient()))
                .ingredientUuid(ingredientView.getId().getIngredientUuid())
                .patternUuid(ingredientView.getId().getPatternUuid())
                .quantity(ingredientView.getQuantity())
                .build();

    }
}
