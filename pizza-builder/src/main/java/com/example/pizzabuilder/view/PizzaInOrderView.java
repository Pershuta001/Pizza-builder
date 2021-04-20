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
public class PizzaInOrderView {
    private UUID pizzaPatternUUID;

    private Integer ordersUUID;

    private Integer pizzaSize;

    private Integer quantity;
    private Double price;

}
