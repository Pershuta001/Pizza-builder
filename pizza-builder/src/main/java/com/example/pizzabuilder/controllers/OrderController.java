package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.sevices.OrderService;
import com.example.pizzabuilder.view.OrderView;
import com.example.pizzabuilder.view.UserViewSignUp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
    private final OrderService orderService;

    @ResponseBody
    @PreAuthorize("hasAuthority('ORDER_CREATE')")
    @PostMapping("/create-cart")
    public ResponseEntity<String> addNewOrderToCart(
            @RequestBody OrderView orderView
            ){
        Order order = orderService.s
        UserEntity userEntity = userService.saveNewUser(userViewSignUp);
        HttpHeaders headers  = new HttpHeaders();
        UserDetails userDetails = applicationUserService.loadUserByUsername(userEntity.getEmail());
        headers.set("Authorization", applicationUserService.generateToken(userDetails.getUsername(), userDetails.getAuthorities()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(applicationUserService.responseUser());
    }
}
