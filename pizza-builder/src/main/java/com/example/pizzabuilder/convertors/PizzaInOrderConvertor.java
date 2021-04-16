package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.PizzaInOrderId;
import com.example.pizzabuilder.view.PizzaInOrderView;
import org.springframework.stereotype.Component;

@Component
public class PizzaInOrderConvertor {
    public PizzaInOrder convert(PizzaInOrderView pizzaInOrderView){
        return PizzaInOrder.builder()
                .id(PizzaInOrderId.builder()
                        .ordersUUID(pizzaInOrderView.getOrdersUUID())
                        .pizzaPatternUUID(pizzaInOrderView.getPizzaPatternUUID())
                        .pizzaSize(pizzaInOrderView.getPizzaSize())
                        .build())
                .quantity(pizzaInOrderView.getQuantity())
                .build();
    }
}
