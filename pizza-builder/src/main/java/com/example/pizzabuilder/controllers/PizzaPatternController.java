package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.convertors.PizzaPatternConvertor;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.sevices.PizzaPatternService;
import com.example.pizzabuilder.view.PizzaPatternView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PizzaPatternController {
    private final PizzaPatternService patternService;
    private final PizzaPatternConvertor pizzaPatternConvertor;

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
