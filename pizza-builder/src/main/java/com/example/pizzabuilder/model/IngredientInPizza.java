package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ingredient_in_pizza")
public class IngredientInPizza {

    @EmbeddedId
    private IngredientInPizzaId id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
