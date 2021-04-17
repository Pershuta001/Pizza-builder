package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.PizzaPatternConvertor;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.repositories.PizzaPatternRepository;
import com.example.pizzabuilder.view.PizzaPatternView;
import com.sun.javaws.exceptions.ExitException;
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
            throw new Exception("e");
        PizzaPattern pattern = pizzaPattern.get();
        pattern.setConfirmed(confirmedStatus);
        return pizzaPatternRepository.saveAndFlush(pattern);
    }
    @Transactional
    public  PizzaPattern getById(UUID uuid) throws Exception{
        Optional<PizzaPattern> pizzaPattern = pizzaPatternRepository.findById(uuid);
        if(!pizzaPattern.isPresent())
            throw new Exception("e");
        return pizzaPattern.get();
    }

    @SneakyThrows
    @Transactional
    public PizzaPattern savePizzaPattern(PizzaPatternView pizzaPatternView){
        if(pizzaPatternRepository.getByName(pizzaPatternView.getName()).isPresent()){
            throw new Exception("Pattern with such name already exists");
        }
        return pizzaPatternRepository.save(pizzaPatternConvertor.convert(pizzaPatternView));
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
