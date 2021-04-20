package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.convertors.PizzaInOrderConvertor;
import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.model.Address;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.sevices.OrderService;
import com.example.pizzabuilder.sevices.PizzaInOrderService;
import com.example.pizzabuilder.view.OrderResponse;
import com.example.pizzabuilder.view.OrderView;
import com.example.pizzabuilder.view.PizzaInOrderView;
import com.example.pizzabuilder.view.PizzaInOrderWithPatternName;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final PizzaInOrderService pizzaInOrderService;
    private final PizzaInOrderConvertor pizzaInOrderConvertor;

    @ResponseBody
    @PostMapping("/cart/add")
    @PreAuthorize("hasAuthority('order:create')")
    public ResponseEntity<String> addNewOrderToCart(
            @RequestBody OrderView orderView
            ){
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderService.saveOrderToCart(orderView, email);
        return ResponseEntity
                .ok()
                .body(orderService.responseOrder(order));
    }

    @ResponseBody
    @PutMapping("/cart/order")
    @PreAuthorize("hasAuthority('order:create')")
    public ResponseEntity<String> confirmOrder(
            @RequestBody UUID orderUuid,
            @RequestBody Address address
            ){
        Order order = orderService.confirmOrder(orderUuid, address);
        return ResponseEntity
                .ok()
                .body(orderService.responseOrder(order));
    }

    @ResponseBody
    @GetMapping("/cart")
    @PreAuthorize("hasAuthority('pizza_pattern:read')")
    public ResponseEntity<List<PizzaInOrderWithPatternName>> getCart() throws EntityNotExistsException {
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity
                .ok()
                .body(pizzaInOrderConvertor.convert(pizzaInOrderService.getUserCart(email)));
    }




    @ResponseBody
    @PutMapping("/update-order")
    @PreAuthorize("hasAuthority('order:update')")
    public ResponseEntity<String> updateOrder(
            @RequestBody OrderView orderView
    ) throws EntityNotExistsException {
        Order order = null;//orderService.updateOrder(orderView, email);
        return ResponseEntity
                .ok()
                .body(orderService.responseOrder(order));
    }


    @ResponseBody
    @PostMapping("/create-pizza-in-order")
    @PreAuthorize("hasAuthority('pizza_in_order:create')")
    public ResponseEntity<String> addNewPizzaInOrder(
            @RequestBody PizzaInOrderView pizzaInOrderView
            ) throws Exception {
        PizzaInOrder pizzaInOrder = pizzaInOrderService.saveNewPizzaInOrder(pizzaInOrderView);
        return ResponseEntity
                .ok()
                .body(pizzaInOrderService.responsePizzaInOrder(pizzaInOrder));
    }
    @SneakyThrows
    @ResponseBody
    @PutMapping("/update-pizza-in-order")
    @PreAuthorize("hasAuthority('pizza_in_order:update')")
    public ResponseEntity<String> updatePizzaInOrder(
            @RequestBody PizzaInOrderView pizzaInOrderView
    ) {
        PizzaInOrder pizzaInOrder = pizzaInOrderService.updatePizzaInOrder(pizzaInOrderView);
        return ResponseEntity
                .ok()
                .body(pizzaInOrderService.responsePizzaInOrder(pizzaInOrder));
    }

    @DeleteMapping("/delete-pizza-in-order")
    @PreAuthorize("hasAuthority('pizza_in_order:delete')")
    public ResponseEntity<String> deletePizzaInOrder(
            @RequestBody PizzaInOrderView pizzaInOrderView
    ) {
        pizzaInOrderService.delete(pizzaInOrderView);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
