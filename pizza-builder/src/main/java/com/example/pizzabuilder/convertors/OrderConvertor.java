package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.view.OrderView;
import org.springframework.stereotype.Component;

@Component
public class OrderConvertor {
    public Order convert(OrderView orderView){
        return Order.builder()
                .address(orderView.getAddress())
                .dataTime(orderView.getDate())
                .status(orderView.getStatus())
                .totalPrice(orderView.getTotalPrice())
                .build();
    }
}
