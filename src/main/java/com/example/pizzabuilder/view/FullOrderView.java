package com.example.pizzabuilder.view;

import com.example.pizzabuilder.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullOrderView {

    private Integer checkId;
    private Address address;
    private String userName;
    private String status;
    private List<PizzaInOrderWithPatternName> patternViewList;
    private Double totalPrice;

}
