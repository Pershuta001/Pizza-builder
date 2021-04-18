package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.convertors.IngredientConvertor;
import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.sevices.IngredientGroupService;
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
    private final IngredientGroupService ingredientGroupService;
    private final IngredientConvertor ingredientConvertor;

    @ResponseBody
    @GetMapping("/products/all")
    @PreAuthorize("hasAuthority('ingredient:read')")
    public ResponseEntity<List<IngredientView>> getAllIngredients(
    ){
        return ResponseEntity
                .ok()
                .body(ingredientService.getAll());
    }

    @ResponseBody
    @PostMapping("/products/add")
    @PreAuthorize("hasAuthority('ingredient:create')")
    public ResponseEntity<IngredientView> addIngredient(
            @RequestBody IngredientView ingredientView
            ) throws Exception {
        Ingredient ingredient = ingredientService.save(ingredientView);
        ingredientGroupService.addIngredient(ingredient.getGroupUuid().getUuid(), ingredient);
        return ResponseEntity
                .ok()
                .body(ingredientConvertor.convert(ingredient));
    }
}
