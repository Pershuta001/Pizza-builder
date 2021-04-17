package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.sevices.PizzaPatternService;
import com.example.pizzabuilder.view.PizzaPatternView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PizzaPatternController {
    private final PizzaPatternService patternService;

    @ResponseBody
    @PreAuthorize("hasAuthority('pizza_pattern:read')")
    @GetMapping("/patterns/all")
    public ResponseEntity<List<PizzaPattern>> getAllPatterns(
    ){

        return ResponseEntity
                .ok()
                .body(patternService.getAll());
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('pizza_pattern:create')")
    @PostMapping("/patterns/add")
    public ResponseEntity<PizzaPattern> saveNewPattern(
            @RequestBody PizzaPatternView pizzaPattern
    ){
        return ResponseEntity
                .ok()
                .body(patternService.savePizzaPattern(pizzaPattern));
    }
}
