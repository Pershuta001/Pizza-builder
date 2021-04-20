package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.OrderConvertor;
import com.example.pizzabuilder.enums.OrderStatusEnum;
import com.example.pizzabuilder.enums.RolesEnum;
import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.model.*;
import com.example.pizzabuilder.repositories.*;
import com.example.pizzabuilder.view.OrderView;
import com.example.pizzabuilder.view.UserViewSignUp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PizzaInOrderRepository pizzaInOrderRepository;
    private final PizzaPatternRepository pizzaPatternRepository;
    private final OrderConvertor orderConvertor;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public Order setStatus(UUID orderId, OrderStatusEnum status) throws Exception {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent())
            throw new Exception("No order with id " + orderId.toString());
        Order order = orderOptional.get();
        order.setStatus(status);
        return orderRepository.saveAndFlush(order);
    }

    @Transactional
    public List<Order> getByStatus(OrderStatusEnum status) {
        return orderRepository.findByStatus(status);
    }

    @Transactional
    public List<Order> getUserCart(String email) throws EntityNotExistsException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (!userEntity.isPresent())
            throw new EntityNotExistsException(UserEntity.class, email);
        return orderRepository.findByStatusAndUserEntity(OrderStatusEnum.IN_CART, userEntity.get());
    }

//    @Transactional
//    public Order updateOrder(OrderView orderView, String email) throws EntityNotExistsException {
//        Optional<Order> optionalOrder = orderRepository.findByUuid(orderView.getUuid());
//        if (!optionalOrder.isPresent())
//            throw new EntityNotExistsException(UserEntity.class, orderView.getUuid());
//        Order order = optionalOrder.get();
//        //TODO how to count total price
//        order.setTotalPrice(orderView.getTotalPrice());
//        order.setDataTime(orderView.getDate());
//        order.setStatus(orderView.getStatus());
//        order.setAddress(orderView.getAddress());
//        return orderRepository.saveAndFlush(order);
//
//
//    }

    @Transactional
    public Order saveOrderToCart(OrderView newOrder, String email) {

        Order order = orderConvertor.convert(newOrder);
        order.setUserEntity(userRepository.findByEmail(email).get());
        order.setStatus(OrderStatusEnum.IN_CART);
        order.setTotalPrice(100.);
        order = orderRepository.save(order);

        PizzaInOrder pizzaInOrder = new PizzaInOrder();
        pizzaInOrder.setId(new PizzaInOrderId(newOrder.getPattern(), order.getId(), newOrder.getSize()));
        pizzaInOrder.setQuantity(newOrder.getAmount());
        pizzaInOrder.setPrice(100.);

        pizzaInOrderRepository.save(pizzaInOrder);
        return order;
    }

    @SneakyThrows
    @Transactional
    public Order confirmOrder(UUID orderUuid, Address address) {
        Optional<Order> orderOptional = orderRepository.findById(orderUuid);
        if (!orderOptional.isPresent())
            throw new Exception("No order with id: " + orderUuid);
        Order order = orderOptional.get();
        order.setStatus(OrderStatusEnum.ORDERED);
        order.setAddress(address);
        return orderRepository.save(order);
    }

    @SneakyThrows
    public String responseOrder(Order order) {
        String res = "{";
        res += String.format("\"uuid\": \"%s\",", objectMapper.writeValueAsString(order.getId()));

        res += String.format("\"status\": \"%s\",", objectMapper.writeValueAsString(order.getStatus()));
        res += String.format("\"date\": \"%s\",", objectMapper.writeValueAsString(order.getDataTime()));
        res += String.format("\"totalPrice\": \"%s\",", objectMapper.writeValueAsString(order.getTotalPrice()));
        res += String.format("\"pizzaInOrder\": \"%s\",", objectMapper.writeValueAsString(order.getPizzaInOrders()));
        res += "\"address\":" + objectMapper.writeValueAsString(order.getAddress()) + "}";
        return res;
    }
    //TODO exceptions timeFinding
    /*
    getByDate criteria
     */
}
