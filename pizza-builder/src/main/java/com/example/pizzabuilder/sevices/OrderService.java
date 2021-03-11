package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    @Transactional
    public Order setStatus(UUID orderId, Integer status) throws Exception{
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(!orderOptional.isPresent())
            throw new Exception("e");
        Order order = orderOptional.get();
        order.setStatus(status);
        return orderRepository.saveAndFlush(order);
    }
    @Transactional
    public List<Order> getByStatus(Integer status){
        return orderRepository.findByStatus(status);
    }
    //TODO exceptions timeFinding
    /*
    getByDate criteria
     */
}
