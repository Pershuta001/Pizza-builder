package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.repositories.PizzaPatternRepository;
import com.example.pizzabuilder.view.PizzaPatternView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PizzaPatternService {
    private final PizzaPatternRepository pizzaPatternRepository;

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

    @Transactional
    public PizzaPattern savePizzaPattern(PizzaPatternView pizzaPatternView){
        return pizzaPatternRepository.save(PizzaPattern.builder()
                .name(pizzaPatternView.getName())
                .confirmed(pizzaPatternView.getConfirmed())
                .userEntityUUID(pizzaPatternView.getUserEntityUUID())
                .pizzaInOrders(pizzaPatternView.getPizzaInOrders())
                .photo(pizzaPatternView.getPhoto()).build());
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
