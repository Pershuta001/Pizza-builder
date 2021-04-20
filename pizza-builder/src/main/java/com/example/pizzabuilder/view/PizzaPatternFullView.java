package com.example.pizzabuilder.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PizzaPatternFullView {
    private UUID uuid;
    private String name;
    private UUID userEntityUUID;
    private List<IngredientInPizzaFullView> ingredients;
    private String photoUrl;
    private Boolean confirmed;
}
