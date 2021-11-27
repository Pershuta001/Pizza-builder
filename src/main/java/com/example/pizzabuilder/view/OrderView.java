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
public class OrderView {

    private UUID pattern;
    private Integer amount;
    private Integer size;
    private Double price;

}
