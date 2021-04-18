package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.PizzaInOrderConvertor;
import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.model.*;
import com.example.pizzabuilder.repositories.PizzaInOrderRepository;
import com.example.pizzabuilder.view.PizzaInOrderView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PizzaInOrderService {
    private final PizzaInOrderRepository pizzaInOrderRepository;
    private final PizzaInOrderConvertor pizzaInOrderConvertor;
    private final ObjectMapper objectMapper= new ObjectMapper();

    @Transactional
    public  PizzaInOrder saveNewPizzaInOrder(PizzaInOrderView pizzaInOrderView){
        PizzaInOrder pizzaInOrder = pizzaInOrderConvertor.convert(pizzaInOrderView);
        //TODO how to count price
        pizzaInOrder.setPrice(100.0);
        return pizzaInOrderRepository.save(pizzaInOrder);
    }
    @Transactional
    public PizzaInOrder setQuantity(PizzaInOrderId inOrderId, Integer quantity) throws Exception{
        Optional<PizzaInOrder> pizzaInOrderOptional = pizzaInOrderRepository.findById(inOrderId);
        if(!pizzaInOrderOptional.isPresent())
            throw new Exception("No pizza in order with id "+ inOrderId.toString());
        PizzaInOrder pizzaInOrder = pizzaInOrderOptional.get();
        pizzaInOrder.setQuantity(quantity);
        return pizzaInOrderRepository.saveAndFlush(pizzaInOrder);
    }
    @Transactional
    public void delete(PizzaInOrderView pizzaInOrderId){
        pizzaInOrderRepository.deleteById(pizzaInOrderConvertor.convert(pizzaInOrderId).getId());
    }
    @SneakyThrows
    public String responsePizzaInOrder(PizzaInOrder pizzaInOrder){
        String res = "{";
        res += String.format("\"orderId\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getId().getOrdersUUID()));
        res += String.format("\"pattern\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getPizzaPattern()));
        res += String.format("\"totalPrice\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getPrice()));
        res += String.format("\"quantity\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getQuantity()));
        res += "\"size\":"+objectMapper.writeValueAsString(pizzaInOrder.getId().getPizzaSize())+"}";
        return res;
    }
    @Transactional
    public PizzaInOrder updatePizzaInOrder(PizzaInOrderView pizzaInOrderView) throws EntityNotExistsException {
        PizzaInOrder pizzaInOrder = pizzaInOrderConvertor.convert(pizzaInOrderView);
        Optional<PizzaInOrder> pizzaInOrderOptional = pizzaInOrderRepository.findById(pizzaInOrder.getId());
        if(!pizzaInOrderOptional.isPresent())
            throw new EntityNotExistsException(UserEntity.class, pizzaInOrder.getId());
        PizzaInOrder pizzaInOrderDB = pizzaInOrderOptional.get();
        pizzaInOrderDB.setQuantity(pizzaInOrder.getQuantity());
        //TODO price...
        pizzaInOrderDB.setPrice(200.0);
        return pizzaInOrderRepository.saveAndFlush(pizzaInOrderDB);
    }
}
