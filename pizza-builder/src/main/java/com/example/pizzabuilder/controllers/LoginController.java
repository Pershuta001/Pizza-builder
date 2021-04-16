package com.example.pizzabuilder.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    @ResponseBody
    @PostMapping("login")
    public void login() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }


}
