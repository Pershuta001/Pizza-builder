package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ingredient_in_pizza")
public class IngredientInPizza {

    @EmbeddedId
    private IngredientInPizzaId id;
    @ManyToOne
    @JoinColumn(name = "pizza_pattern_uuid", insertable = false, updatable = false)
    private PizzaPattern pizzaPattern;

    @ManyToOne
    @JoinColumn(name = "ingrediens_uuid", insertable = false, updatable = false)
    private Ingredient ingredient;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
