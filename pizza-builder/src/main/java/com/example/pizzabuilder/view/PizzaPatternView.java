package com.example.pizzabuilder.view;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.UserEntity;
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
public class PizzaPatternView {
    private UUID uuid;
    private String name;
    private UUID userEntityUUID;
    private List<IngredientInPizzaView> ingredients;
    private String photoUrl;
    private Boolean confirmed;
}
