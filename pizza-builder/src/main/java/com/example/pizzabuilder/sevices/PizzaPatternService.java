package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.PizzaPatternConvertor;
import com.example.pizzabuilder.model.*;
import com.example.pizzabuilder.repositories.IngredientRepository;
import com.example.pizzabuilder.repositories.IngredientsInPizzaRepository;
import com.example.pizzabuilder.repositories.PizzaPatternRepository;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.view.PizzaPatternView;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PizzaPatternService {
    private final PizzaPatternRepository pizzaPatternRepository;
    private final UserRepository userRepository;
    private final PizzaPatternConvertor pizzaPatternConvertor;
    private final IngredientsInPizzaRepository ingredientsInPizzaRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public List<PizzaPattern> getAll() {
        return pizzaPatternRepository.findAll();
    }

    @Transactional
    public List<PizzaPattern> getAllForUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return pizzaPatternRepository.getAllByUserEntityUUID(userRepository.findByEmail(email).get());
    }

    @Transactional
    PizzaPattern save(PizzaPattern pizzaPattern) {
        return pizzaPatternRepository.save(pizzaPattern);
    }

    @Transactional
    public PizzaPattern setConfirmed(UUID patternId, Boolean confirmedStatus) throws Exception {
        Optional<PizzaPattern> pizzaPattern = pizzaPatternRepository.findById(patternId);
        if (!pizzaPattern.isPresent())
            throw new Exception("No pizza pattern with id " + patternId.toString());
        PizzaPattern pattern = pizzaPattern.get();
        pattern.setConfirmed(confirmedStatus);
        return pizzaPatternRepository.saveAndFlush(pattern);
    }

    @Transactional
    public PizzaPattern getById(UUID uuid) throws Exception {
        Optional<PizzaPattern> pizzaPattern = pizzaPatternRepository.findById(uuid);
        if (!pizzaPattern.isPresent())
            throw new Exception("No pizza pattern with id " + uuid.toString());
        return pizzaPattern.get();
    }

    @SneakyThrows
    @Transactional
    public PizzaPattern savePizzaPattern(PizzaPatternView pizzaPatternView) {
        if (pizzaPatternRepository.getByName(pizzaPatternView.getName()).isPresent()) {
            throw new Exception("Pattern with such name already exists");
        }
        PizzaPattern converted = pizzaPatternConvertor.convert(pizzaPatternView);
        converted.setConfirmed(false);
        PizzaPattern pizzaPattern = pizzaPatternRepository.save(converted);
        List<IngredientInPizza> ingredientInPizzas = new ArrayList<>();
        for (IngredientInPizza i : pizzaPattern.getIngredients()) {
            i.getId().setPatternUuid(pizzaPattern.getUuid());
            ingredientInPizzas.add(ingredientsInPizzaRepository.save(i));
        }

        return pizzaPattern;
    }

    @Transactional
    public PizzaPattern updatePizzaPattern(PizzaPattern pizzaPattern) {
        return pizzaPatternRepository.saveAndFlush(pizzaPattern);
    }
    /*
    getWithCriteriaParams
    getSortedWithCriteriaParams
     */
}
