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
}
