package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.view.IngredientInPizzaFullView;
import com.example.pizzabuilder.view.IngredientInPizzaView;
import com.example.pizzabuilder.view.PizzaPatternFullView;
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

    public PizzaPattern convert(PizzaPatternView pizzaPatternView) {
        return PizzaPattern.builder()
                .name(pizzaPatternView.getName())
                .userEntityUUID(userRepository.findByUuid(pizzaPatternView.getUserEntityUUID()).get())
                .ingredients(convert(pizzaPatternView.getIngredients()))
                .photoUrl(pizzaPatternView.getPhotoUrl())
                .uuid(pizzaPatternView.getUuid())
                .build();
    }

    public List<IngredientInPizza> convert(List<IngredientInPizzaView> ingredientViews) {
        List<IngredientInPizza> res = new ArrayList<>();
        for (IngredientInPizzaView ingredientView : ingredientViews) {
            res.add(ingredientConvertor.convert(ingredientView));
        }
        return res;
    }

    public PizzaPatternView convert(PizzaPattern pizzaPattern) {
        return PizzaPatternView.builder()
                .ingredients(convertToViews(pizzaPattern.getIngredients()))
                .name(pizzaPattern.getName())
                .photoUrl(pizzaPattern.getPhotoUrl())
                .userEntityUUID(pizzaPattern.getUserEntityUUID().getUuid())
                .uuid(pizzaPattern.getUuid())
                .confirmed(pizzaPattern.getConfirmed())
                .build();
    }

    public List<IngredientInPizzaView> convertToViews(List<IngredientInPizza> ingredientViews) {
        List<IngredientInPizzaView> res = new ArrayList<>();
        for (IngredientInPizza ingredient : ingredientViews) {
            res.add(ingredientConvertor.convert(ingredient));
        }
        return res;
    }

    public PizzaPatternFullView convertFull(PizzaPattern pizzaPattern) {
        return PizzaPatternFullView.builder()
                .ingredients(convertToFullViews(pizzaPattern.getIngredients()))
                .name(pizzaPattern.getName())
                .photoUrl(pizzaPattern.getPhotoUrl())
                .userEntityUUID(pizzaPattern.getUserEntityUUID().getUuid())
                .uuid(pizzaPattern.getUuid())
                .confirmed(pizzaPattern.getConfirmed())
                .build();
    }

    public List<IngredientInPizzaFullView> convertToFullViews(List<IngredientInPizza> ingredientViews) {
        List<IngredientInPizzaFullView> res = new ArrayList<>();
        for (IngredientInPizza ingredient : ingredientViews) {
            res.add(ingredientConvertor.convertFull(ingredient));
        }
        return res;
    }
}
