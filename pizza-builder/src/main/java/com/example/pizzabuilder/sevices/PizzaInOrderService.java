package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.PizzaInOrder;
import com.example.pizzabuilder.model.PizzaInOrderId;
import com.example.pizzabuilder.repositories.OrderRepository;
import com.example.pizzabuilder.repositories.PizzaInOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PizzaInOrderService {
    private final PizzaInOrderRepository pizzaInOrderRepository;

    @Transactional
    public PizzaInOrder setQuantity(PizzaInOrderId inOrderId, Integer quantity) throws Exception{
        Optional<PizzaInOrder> pizzaInOrderOptional = pizzaInOrderRepository.findById(inOrderId);
        if(!pizzaInOrderOptional.isPresent())
            throw new Exception("e");
        PizzaInOrder pizzaInOrder = pizzaInOrderOptional.get();
        pizzaInOrder.setQuantity(quantity);
        return pizzaInOrderRepository.saveAndFlush(pizzaInOrder);
    }
    @Transactional
    public void delete(PizzaInOrderId pizzaInOrderId){
        pizzaInOrderRepository.deleteById(pizzaInOrderId);
    }
}
