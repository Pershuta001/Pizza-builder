package com.example.pizzabuilder.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @ResponseBody
    @PostMapping("login")
    public void login() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }


}
