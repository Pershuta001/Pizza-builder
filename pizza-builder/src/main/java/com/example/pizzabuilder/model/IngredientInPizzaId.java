package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientInPizzaId implements Serializable {

    @Column(name = "pattern_uuid")
    private Integer patternUuid;
    @Column(name = "ingredient_uuid")
    private Integer ingredientUuid;

}
