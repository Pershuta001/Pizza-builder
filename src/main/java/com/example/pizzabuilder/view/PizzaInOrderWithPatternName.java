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
public class PizzaInOrderWithPatternName {
    private String name;
    private String photoUrl;
    private UUID patternUuid;
    private Integer quantity;
    private Double price;
    private Integer size;

}
