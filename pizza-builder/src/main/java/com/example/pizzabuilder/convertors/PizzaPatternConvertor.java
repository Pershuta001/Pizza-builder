package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.view.IngredientView;
import com.example.pizzabuilder.view.PizzaPatternView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PizzaPatternConvertor {

    private final IngredientConvertor ingredientConvertor;
    private final UserRepository userRepository;

    public PizzaPattern convert(PizzaPatternView pizzaPatternView){
        return PizzaPattern.builder()
                .name(pizzaPatternView.getName())
                .confirmed(pizzaPatternView.getConfirmed())
                .userEntityUUID(userRepository.findByUuid(pizzaPatternView.getUserEntityUUID()).get())
                .ingredients(convert(pizzaPatternView.getIngredients()))
                .photoUrl(pizzaPatternView.getPhotoUrl())
                .uuid(pizzaPatternView.getUuid())
                .build();
    }
    public List<Ingredient> convert(List<IngredientView> ingredientViews){
        List<Ingredient> res = new ArrayList<>();
        for(IngredientView ingredientView: ingredientViews){
            res.add(ingredientConvertor.convert(ingredientView));
        }
        return res;
    }
}
