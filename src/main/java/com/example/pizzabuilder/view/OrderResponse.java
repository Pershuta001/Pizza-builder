package com.example.pizzabuilder.view;

import com.example.pizzabuilder.model.PizzaInOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private List<OrderView> patternsInOrder;
    private Double totalPrice;

    public static OrderResponse convert(List<PizzaInOrder> patterns) {

        List<OrderView> res = new ArrayList<>();
        Double totalPrice = 0.0;
        for (PizzaInOrder pat : patterns) {
            totalPrice += pat.getPrice();
            res.add(OrderView.builder()
                    .amount(pat.getQuantity())
                    .pattern(pat.getId().getPizzaPatternUUID())
                    .size(pat.getId().getPizzaSize())
                    .price(pat.getPrice()).build());
        }
        return OrderResponse.builder().patternsInOrder(res).totalPrice(totalPrice).build();
    }

}
