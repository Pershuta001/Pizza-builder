package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientInPizzaId implements Serializable {

    @Column(name = "pattern_uuid")
    private UUID patternUuid;
    @Column(name = "ingredient_uuid")
    private UUID ingredientUuid;

}
