package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.model.User;
import com.example.pizzabuilder.sevices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseBody
    @GetMapping("/exist")
    public Optional<User> existByEmail(
            @RequestParam String email
    ){
        return userService.existByEmail(email);
    }
}
