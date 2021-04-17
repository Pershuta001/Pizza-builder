package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.sevices.IngredientService;
import com.example.pizzabuilder.view.IngredientView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class IngredientsController {

    private final IngredientService ingredientService;

    @ResponseBody
    @PreAuthorize("hasAuthority('ingredient:read')")
    @GetMapping("/products/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients(
    ){
        return ResponseEntity
                .ok()
                .body(ingredientService.getAll());
    }

    @ResponseBody
    @PostMapping("/products/add")
    @PreAuthorize("hasAuthority('ingredient:create')")
    public ResponseEntity<Ingredient> addIngredient(
            @RequestBody IngredientView ingredientView
            ){
        return ResponseEntity
                .ok()
                .body(ingredientService.save(ingredientView));
    }
}
