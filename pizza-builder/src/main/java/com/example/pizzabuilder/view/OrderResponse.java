package com.example.pizzabuilder.view;

import com.example.pizzabuilder.model.Address;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.UserEntity;
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

    public static OrderResponse convert(List<PizzaInOrder> patterns) {

        List<OrderView> res = new ArrayList<>();
        for (PizzaInOrder pat : patterns) {
            res.add(OrderView.builder()
                    .amount(pat.getQuantity())
                    .pattern(pat.getId().getPizzaPatternUUID())
                    .size(pat.getId().getPizzaSize()).build());
        }
        return OrderResponse.builder().patternsInOrder(res).build();
    }

}
