package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.view.IngredientInPizzaView;
import com.example.pizzabuilder.view.IngredientView;
import com.example.pizzabuilder.view.PizzaPatternView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PizzaPatternConvertor {

    private final IngredientInPizzaConvertor ingredientConvertor;
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
    public List<IngredientInPizza> convert(List<IngredientInPizzaView> ingredientViews){
        List<IngredientInPizza> res = new ArrayList<>();
        for(IngredientInPizzaView ingredientView: ingredientViews){
            res.add(ingredientConvertor.convert(ingredientView));
        }
        return res;
    }
    public PizzaPatternView convert(PizzaPattern pizzaPattern){
        return PizzaPatternView.builder()
                .confirmed(pizzaPattern.getConfirmed())
                .ingredients(convertToViews(pizzaPattern.getIngredients()))
                .name(pizzaPattern.getName())
                .photoUrl(pizzaPattern.getPhotoUrl())
                .userEntityUUID(pizzaPattern.getUserEntityUUID().getUuid())
                .uuid(pizzaPattern.getUuid())
                .build();
    }
    public List<IngredientInPizzaView> convertToViews(List<IngredientInPizza> ingredientViews){
        List<IngredientInPizzaView> res = new ArrayList<>();
        for(IngredientInPizza ingredient: ingredientViews){
            res.add(ingredientConvertor.convert(ingredient));
        }
        return res;
    }
}
