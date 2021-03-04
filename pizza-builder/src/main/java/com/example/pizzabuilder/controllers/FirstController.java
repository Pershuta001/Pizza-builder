package com.example.pizzabuilder.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<String> firstResponse(){
       return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
