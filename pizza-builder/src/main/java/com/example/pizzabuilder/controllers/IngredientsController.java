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
import java.util.UUID;

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
    ) {
        return ResponseEntity
                .ok()
                .body(ingredientService.getAll());
    }

    @ResponseBody
    @GetMapping("/products/{groupUuid}")
    @PreAuthorize("hasAuthority('ingredient:read')")
    public ResponseEntity<List<IngredientView>> getAllIngredients(@PathVariable UUID groupUuid
    ) {
        return ResponseEntity
                .ok()
                .body(ingredientService.getByGroup(groupUuid));
    }

    @ResponseBody
    @PostMapping("/products/add")
    @PreAuthorize("hasAuthority('ingredient:create')")
    public ResponseEntity<IngredientView> addIngredient(
            @RequestBody IngredientView ingredientView
    ) throws Exception {
        Ingredient ingredient = ingredientService.save(ingredientView);
        if (ingredient.getGroupUuid() != null)
            ingredientGroupService.addIngredient(ingredient.getGroupUuid().getUuid(), ingredient);
        return ResponseEntity
                .ok()
                .body(ingredientConvertor.convert(ingredient));
    }
    @DeleteMapping("/products/{uuid}")
    @PreAuthorize("hasAuthority('ingredient:delete')")
    public ResponseEntity deleteIngredient(@PathVariable UUID uuid
    ) {
        try {
            ingredientService.delete(uuid);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity
                .ok().build();
    }
}
