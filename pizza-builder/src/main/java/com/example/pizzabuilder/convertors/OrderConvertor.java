package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.enums.OrderStatusEnum;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.repositories.PizzaPatternRepository;
import com.example.pizzabuilder.view.OrderView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConvertor {


    public Order convert(OrderView orderView) {
        return Order.builder()
                .address(null)
                .dataTime(Date.valueOf(LocalDate.now()))
                .status(OrderStatusEnum.IN_CART)
                .build();
    }

//    public OrderView convert(Order order) {
//        return OrderView.builder()
//                .amount(order.get)
//                .build();
//    }

    public List<OrderView> convert(List<Order> orders) {
        List<OrderView> res = new ArrayList<>();
        for (Order ord : orders) {

        }

        return null;
    }
}
