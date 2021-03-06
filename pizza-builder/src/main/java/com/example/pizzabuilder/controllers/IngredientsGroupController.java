package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.IngredientGroup;
import com.example.pizzabuilder.sevices.IngredientGroupService;
import com.example.pizzabuilder.view.IngredientGroupView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class IngredientsGroupController {

    private final IngredientGroupService ingredientGroupService;
    @ResponseBody
    @GetMapping("/products/group/all")
    public ResponseEntity<List<IngredientGroupView>> getAllGroups(
    ) {
        return ResponseEntity
                .ok()
                .body(ingredientGroupService.getAll());
    }

    @ResponseBody
    @PostMapping("/products/group/add")
    @PreAuthorize("hasAuthority('ingredient:create')")
    public ResponseEntity<IngredientGroup> addGroup(
            @RequestBody IngredientGroupView ingredientView
    ) throws Exception {
        return ResponseEntity
                .ok()
                .body(ingredientGroupService.save(ingredientView));
    }

    @ResponseBody
    @PostMapping("/products/group/addIngredients/{groupUUID}")
    @PreAuthorize("hasAuthority('ingredient:create')")
    public ResponseEntity<IngredientGroup> addIngredientsToGroup(
            @RequestBody List<Ingredient> ingredient,
            @PathVariable UUID groupUUID
    ) throws Exception {
        return ResponseEntity
                .ok()
                .body(ingredientGroupService.addIngredients(groupUUID, ingredient));
    }

    @DeleteMapping("/products/group/{uuid}")
    @PreAuthorize("hasAuthority('ingredient:delete')")
    public ResponseEntity deleteGroup(@PathVariable UUID uuid
    ) {
        try {
            ingredientGroupService.delete(uuid);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity
                .ok().build();
    }

}
