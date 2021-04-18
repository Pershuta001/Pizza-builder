package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.PizzaPatternConvertor;
import com.example.pizzabuilder.model.IngredientInPizza;
import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.repositories.IngredientsInPizzaRepository;
import com.example.pizzabuilder.repositories.PizzaPatternRepository;
import com.example.pizzabuilder.view.PizzaPatternView;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PizzaPatternService {
    private final PizzaPatternRepository pizzaPatternRepository;
    private final PizzaPatternConvertor pizzaPatternConvertor;
    private final IngredientsInPizzaRepository ingredientsInPizzaRepository;

    @Transactional
    public List<PizzaPattern> getAll(){
        return pizzaPatternRepository.findAll();
    }
    @Transactional PizzaPattern save(PizzaPattern pizzaPattern){
        return pizzaPatternRepository.save(pizzaPattern);
    }
    @Transactional
    public PizzaPattern setConfirmed(UUID patternId, Boolean confirmedStatus) throws Exception{
        Optional<PizzaPattern> pizzaPattern = pizzaPatternRepository.findById(patternId);
        if(!pizzaPattern.isPresent())
            throw new Exception("No pizza pattern with id "+ patternId.toString());
        PizzaPattern pattern = pizzaPattern.get();
        pattern.setConfirmed(confirmedStatus);
        return pizzaPatternRepository.saveAndFlush(pattern);
    }
    @Transactional
    public  PizzaPattern getById(UUID uuid) throws Exception{
        Optional<PizzaPattern> pizzaPattern = pizzaPatternRepository.findById(uuid);
        if(!pizzaPattern.isPresent())
            throw new Exception("No pizza pattern with id "+ uuid.toString());
        return pizzaPattern.get();
    }

    @SneakyThrows
    @Transactional
    public PizzaPattern savePizzaPattern(PizzaPatternView pizzaPatternView){
        if(pizzaPatternRepository.getByName(pizzaPatternView.getName()).isPresent()){
            throw new Exception("Pattern with such name already exists");
        }
        PizzaPattern pizzaPattern = pizzaPatternConvertor.convert(pizzaPatternView);
        pizzaPattern=pizzaPatternRepository.save(pizzaPatternConvertor.convert(pizzaPatternView));
        for(IngredientInPizza i:pizzaPattern.getIngredients()){
            i.getId().setPatternUuid(pizzaPattern.getUuid());
            ingredientsInPizzaRepository.save(i);}
        return pizzaPattern;
    }

    @Transactional
    public PizzaPattern updatePizzaPattern(PizzaPattern pizzaPattern){
        return pizzaPatternRepository.saveAndFlush(pizzaPattern);
    }
    /*
    getWithCriteriaParams
    getSortedWithCriteriaParams
     */
}
