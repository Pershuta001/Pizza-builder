package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.PizzaInOrderConvertor;
import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.model.*;
import com.example.pizzabuilder.repositories.PizzaInOrderRepository;
import com.example.pizzabuilder.utils.Utils;
import com.example.pizzabuilder.view.PizzaInOrderView;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PizzaInOrderService {
    private final PizzaInOrderRepository pizzaInOrderRepository;
    private final PizzaPatternService pizzaPatternService;
    private final PizzaInOrderConvertor pizzaInOrderConvertor;
    private final ObjectMapper objectMapper= new ObjectMapper();

    @Transactional
    public  PizzaInOrder saveNewPizzaInOrder(PizzaInOrderView pizzaInOrderView) throws Exception {
        PizzaInOrder pizzaInOrder = pizzaInOrderConvertor.convert(pizzaInOrderView);
        pizzaInOrder.setPrice(pizzaInOrder.getQuantity()*Utils.countPatternPrice(pizzaPatternService.getById(pizzaInOrder.getId().getPizzaPatternUUID())));
        return pizzaInOrderRepository.save(pizzaInOrder);
    }

    @Transactional
    public void delete(PizzaInOrderView pizzaInOrderId){
        pizzaInOrderRepository.deleteById(pizzaInOrderConvertor.convert(pizzaInOrderId).getId());
    }
    @SneakyThrows
    public String responsePizzaInOrder(PizzaInOrder pizzaInOrder){
        String res = "{";
        res += String.format("\"orderId\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getId().getOrdersUUID()));
        res += String.format("\"totalPrice\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getPrice()));
        res += String.format("\"quantity\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getQuantity()));
        res += "\"size\":"+objectMapper.writeValueAsString(pizzaInOrder.getId().getPizzaSize())+"}";
        return res;
    }
    @Transactional
    public PizzaInOrder updatePizzaInOrder(PizzaInOrderView pizzaInOrderView) throws Exception {
        PizzaInOrder pizzaInOrder = pizzaInOrderConvertor.convert(pizzaInOrderView);
        Optional<PizzaInOrder> pizzaInOrderOptional = pizzaInOrderRepository.findById(pizzaInOrder.getId());
        if(!pizzaInOrderOptional.isPresent())
            throw new EntityNotExistsException(UserEntity.class, pizzaInOrder.getId());
        PizzaInOrder pizzaInOrderDB = pizzaInOrderOptional.get();
        pizzaInOrderDB.setQuantity(pizzaInOrder.getQuantity());
        pizzaInOrder.setPrice(pizzaInOrder.getQuantity()* Utils.countPatternPrice(pizzaPatternService.getById(pizzaInOrderDB.getId().getPizzaPatternUUID())));
        return pizzaInOrderRepository.saveAndFlush(pizzaInOrderDB);
    }

    @Transactional
    public List<PizzaInOrder> getUserCart(String email) throws EntityNotExistsException {
        System.out.println(email);
        return pizzaInOrderRepository.getCartByUserEmail( email);
    }

}
