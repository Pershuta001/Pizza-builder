package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.convertors.PizzaPatternConvertor;
import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.sevices.IngredientInPizzaService;
import com.example.pizzabuilder.sevices.IngredientService;
import com.example.pizzabuilder.sevices.PizzaPatternService;
import com.example.pizzabuilder.view.PizzaPatternFullView;
import com.example.pizzabuilder.view.PizzaPatternView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class PizzaPatternController {
    private final PizzaPatternService patternService;
    private final PizzaPatternConvertor pizzaPatternConvertor;
    private final IngredientInPizzaService ingredientInPizzaService;
    private final IngredientService ingredientService;

    @ResponseBody
    @PreAuthorize("hasAuthority('pizza_pattern:read')")
    @GetMapping("/patterns/all")
    public ResponseEntity<List<PizzaPatternFullView>> getAllPatterns(
    ){
        List<PizzaPattern> patterns = patternService.getAll();
        List<PizzaPatternFullView> result = new ArrayList<>();
        for(PizzaPattern p:patterns)
            result.add(pizzaPatternConvertor.convertFull(p));

        return ResponseEntity
                .ok()
                .body(result);
    }
    @ResponseBody
    @PreAuthorize("hasAuthority('pizza_pattern:read')")
    @GetMapping("/patterns/{patternUuid}")
    public ResponseEntity<List<IngredientInPizza>> get(@PathVariable UUID patternUuid
    ) throws Exception {
        return ResponseEntity
                .ok()
                .body(ingredientInPizzaService.getByPizzaPattern(patternUuid));
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('pizza_pattern:create')")
    @PostMapping("/patterns/add")
    public ResponseEntity<PizzaPatternView> saveNewPattern(
            @RequestBody PizzaPatternView pizzaPattern
    ){
        return ResponseEntity
                .ok()
                .body(pizzaPatternConvertor.convert(patternService.savePizzaPattern(pizzaPattern)));
    }
    @ResponseBody
    @PreAuthorize("hasAuthority('pizza_pattern:confirm')")
    @PostMapping("/patterns/confirm/{patternUuid}")
    public ResponseEntity<PizzaPatternView> setConfirm(@PathVariable UUID patternUuid
    ) throws Exception {
        return ResponseEntity
                .ok()
                .body(pizzaPatternConvertor.convert(patternService.setConfirmed(patternUuid, true)));
    }
}
