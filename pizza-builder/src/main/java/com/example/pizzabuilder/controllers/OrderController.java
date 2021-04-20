package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.sevices.OrderService;
import com.example.pizzabuilder.sevices.PizzaInOrderService;
import com.example.pizzabuilder.view.OrderView;
import com.example.pizzabuilder.view.PizzaInOrderView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
    private final OrderService orderService;
    private final PizzaInOrderService pizzaInOrderService;

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
            @RequestBody UUID orderUuid
            ){
        Order order = orderService.confirmOrder(orderUuid);
        return ResponseEntity
                .ok()
                .body(orderService.responseOrder(order));
    }



    @ResponseBody
    @PutMapping("/update-order")
    @PreAuthorize("hasAuthority('order:update')")
    public ResponseEntity<String> updateOrder(
            @RequestBody OrderView orderView
    ) throws EntityNotExistsException {
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Order order = orderService.updateOrder(orderView, email);
        return ResponseEntity
                .ok()
                .body(orderService.responseOrder(order));
    }

    @ResponseBody
    @PostMapping("/create-pizza-in-order")
    @PreAuthorize("hasAuthority('pizza_in_order:create')")
    public ResponseEntity<String> addNewPizzaInOrder(
            @RequestBody PizzaInOrderView pizzaInOrderView
            ){
        PizzaInOrder pizzaInOrder = pizzaInOrderService.saveNewPizzaInOrder(pizzaInOrderView);
        return ResponseEntity
                .ok()
                .body(pizzaInOrderService.responsePizzaInOrder(pizzaInOrder));
    }

    @ResponseBody
    @PutMapping("/update-pizza-in-order")
    @PreAuthorize("hasAuthority('pizza_in_order:update')")
    public ResponseEntity<String> updatePizzaInOrder(
            @RequestBody PizzaInOrderView pizzaInOrderView
    ) throws EntityNotExistsException {
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
