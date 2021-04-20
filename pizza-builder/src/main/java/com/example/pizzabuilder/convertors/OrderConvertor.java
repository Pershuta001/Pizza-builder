package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.enums.OrderStatusEnum;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.repositories.PizzaPatternRepository;
import com.example.pizzabuilder.view.FullOrderView;
import com.example.pizzabuilder.view.OrderView;
import com.example.pizzabuilder.view.PizzaInOrderView;
import com.example.pizzabuilder.view.PizzaPatternView;
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

}
