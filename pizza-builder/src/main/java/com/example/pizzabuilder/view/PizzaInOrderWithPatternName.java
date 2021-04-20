package com.example.pizzabuilder.view;

import lombok.*;

import javax.xml.ws.BindingType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PizzaInOrderWithPatternName {
    private String name;
    private String photoUrl;

    private Integer quantity;
    private Double price;
    private Integer size;

}
