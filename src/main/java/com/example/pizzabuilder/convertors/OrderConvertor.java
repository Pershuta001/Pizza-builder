package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.enums.OrderStatusEnum;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.repositories.OrderRepository;
import com.example.pizzabuilder.repositories.PizzaInOrderRepository;
import com.example.pizzabuilder.repositories.PizzaPatternRepository;
import com.example.pizzabuilder.view.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConvertor {


    private final PizzaInOrderRepository pizzaInOrderRepository;
    private final PizzaInOrderConvertor pizzaInOrderConvertor;

    public Order convert(OrderView orderView) {
        return Order.builder()
                .address(null)
                .dataTime(Date.valueOf(LocalDate.now()))
                .status(OrderStatusEnum.IN_CART)
                .build();
    }

    public FullOrderView convert(Order order) {
        List<PizzaInOrder> pizzaInOrders = pizzaInOrderRepository.findAllByOrderId(order.getId());
        List<PizzaInOrderWithPatternName> pizzaInOrderWithPatternNames = pizzaInOrderConvertor.convertWithName(pizzaInOrders);
        return FullOrderView.builder()
                .checkId(order.getId())
                .userName(order.getUserEntity().getName())
                .address(order.getAddress())
                .status(order.getStatus().name())
                .patternViewList(pizzaInOrderWithPatternNames)
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public List<FullOrderView> convert(List<Order> orders) {
        return orders.stream().map(this::convert).collect(Collectors.toList());
    }
}
