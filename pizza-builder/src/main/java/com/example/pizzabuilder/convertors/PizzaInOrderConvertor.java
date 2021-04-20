package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.PizzaInOrderId;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.repositories.PizzaPatternRepository;
import com.example.pizzabuilder.view.PizzaInOrderView;
import com.example.pizzabuilder.view.PizzaInOrderWithPatternName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PizzaInOrderConvertor {

    private final PizzaPatternRepository pizzaPatternRepository;

    public PizzaInOrder convert(PizzaInOrderView pizzaInOrderView) {
        return PizzaInOrder.builder()
                .id(PizzaInOrderId.builder()
                        .ordersUUID(pizzaInOrderView.getOrdersUUID())
                        .pizzaPatternUUID(pizzaInOrderView.getPizzaPatternUUID())
                        .pizzaSize(pizzaInOrderView.getPizzaSize())
                        .build())
                .quantity(pizzaInOrderView.getQuantity())
                .build();
    }

    public PizzaInOrderView convert(PizzaInOrder pizzaInOrder) {
        return PizzaInOrderView.builder()
                .ordersUUID(pizzaInOrder.getId().getOrdersUUID())
                .pizzaPatternUUID(pizzaInOrder.getId().getPizzaPatternUUID())
                .pizzaSize(pizzaInOrder.getId().getPizzaSize())
                .quantity(pizzaInOrder.getQuantity())
                .price(pizzaInOrder.getPrice())
                .build();
    }


    public List<PizzaInOrderWithPatternName> convert(List<PizzaInOrderView> pizzaInOrder) {
        List<PizzaInOrderWithPatternName> res = new ArrayList<>();

        for (PizzaInOrderView pizza : pizzaInOrder) {
            PizzaPattern pattern = pizzaPatternRepository.getOne(pizza.getPizzaPatternUUID());
            res.add(PizzaInOrderWithPatternName.builder()
                    .name(pattern.getName())
                    .photoUrl(pattern.getPhotoUrl())
                    .price(pizza.getPrice())
                    .size(pizza.getPizzaSize())
                    .patternUuid(pizza.getPizzaPatternUUID())
                    .quantity(pizza.getQuantity()).build());
        }
        return res;
    }

    public PizzaInOrderWithPatternName convertWithName(PizzaInOrder pizzaInOrder) {

        PizzaPattern pattern = pizzaPatternRepository.getOne(pizzaInOrder.getId().getPizzaPatternUUID());
        return PizzaInOrderWithPatternName.builder()
                .name(pattern.getName())
                .photoUrl(pattern.getPhotoUrl())
                .price(pizzaInOrder.getPrice())
                .size(pizzaInOrder.getId().getPizzaSize())
                .patternUuid(pizzaInOrder.getId().getPizzaPatternUUID())
                .quantity(pizzaInOrder.getQuantity()).build();
    }

}