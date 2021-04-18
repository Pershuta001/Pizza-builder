package com.example.pizzabuilder.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientInPizzaView {
    private IngredientView ingredients;
    private Integer quantity;
    private UUID patternUuid;
    private UUID ingredientUuid;
}
