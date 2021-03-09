package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.view.UserViewLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {


    @ResponseBody
    @GetMapping("/secured-api")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserViewLogin> firstResponse() {
        return  ResponseEntity.ok(UserViewLogin.builder().email("success").password("password").build());
    }

    @ResponseBody
    @GetMapping("/secured-api-admin")
    public ResponseEntity<UserViewLogin> firstResponseAdmin() {
        return  ResponseEntity.ok(UserViewLogin.builder().email("admin").password("password").build());
    }
}
