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
public class IngredientView {
    private UUID uuid;
    private UUID groupUuid;
    private String name;
    private Double price;
    private Boolean spicy;
    private Boolean vegetarian;
    private Boolean vegan;
    private byte[] photo;
}
