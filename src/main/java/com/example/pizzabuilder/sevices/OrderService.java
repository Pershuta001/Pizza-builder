package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.OrderConvertor;
import com.example.pizzabuilder.convertors.PizzaInOrderConvertor;
import com.example.pizzabuilder.enums.OrderStatusEnum;
import com.example.pizzabuilder.enums.RolesEnum;
import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.model.*;
import com.example.pizzabuilder.repositories.*;
import com.example.pizzabuilder.utils.Utils;
import com.example.pizzabuilder.view.FullOrderView;
import com.example.pizzabuilder.view.OrderView;
import com.example.pizzabuilder.view.PizzaInOrderView;
import com.example.pizzabuilder.view.UserViewSignUp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.NoSuchObjectException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PizzaInOrderRepository pizzaInOrderRepository;
    private final PizzaPatternService pizzaPatternService;
    private final PizzaInOrderService service;
    private final OrderConvertor orderConvertor;
    private final PizzaInOrderConvertor pizzaInOrderConvertor;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<String> getAllStatuses(){
       return Arrays.stream(OrderStatusEnum.values())
               .filter(a -> !a.equals(OrderStatusEnum.IN_CART))
               .map(OrderStatusEnum::name)
               .collect(Collectors.toList());
    }

    @SneakyThrows
    @Transactional
    public Order setStatus(Integer orderId, String status) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent())
            throw new Exception("No order with id " + orderId.toString());
        Order order = orderOptional.get();
        order.setStatus(OrderStatusEnum.valueOf(status));
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

    @Transactional
    public Order saveOrderToCart(OrderView newOrder, String email) {

        UserEntity userEntity = userRepository.findByEmail(email).get();
        List<Order> orders = orderRepository.findByStatusAndUserEntity(OrderStatusEnum.IN_CART, userEntity);
        Order order = null;
        PizzaInOrder pizzaInOrder = null;
        if (orders.isEmpty()) {
            order = orderConvertor.convert(newOrder);
            order.setUserEntity(userEntity);
            order.setStatus(OrderStatusEnum.IN_CART);
            order.setTotalPrice(0.);
            order = orderRepository.save(order);
        } else {
            order = orders.get(0);
        }
        PizzaInOrderId id = new PizzaInOrderId(newOrder.getPattern(), order.getId(), newOrder.getSize());
        Optional<PizzaInOrder> pizzaInOrderOptional = pizzaInOrderRepository.findById(id);
        if (pizzaInOrderOptional.isPresent()) {
            pizzaInOrder = pizzaInOrderOptional.get();
            double price = pizzaInOrder.getPrice()/pizzaInOrder.getQuantity();
            pizzaInOrder.setQuantity(pizzaInOrder.getQuantity() + 1);
            pizzaInOrder.setPrice(pizzaInOrder.getQuantity()*price);
        } else {
            pizzaInOrder = new PizzaInOrder();
            pizzaInOrder.setId(id);
            pizzaInOrder.setQuantity(newOrder.getAmount());
            pizzaInOrder.setPrice(pizzaPatternService.countPrice(newOrder.getPattern()) * newOrder.getSize());
        }
        pizzaInOrderRepository.save(pizzaInOrder);
        order.setTotalPrice(cartPrice());
        return order;
    }

    @SneakyThrows
    @Transactional
    public FullOrderView confirmOrder(Address address) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByEmail(email).get();
        List<Order> all = orderRepository.findAll();
        FullOrderView res = FullOrderView.builder()
                .checkId(new Random().nextInt() * 100000)
                .totalPrice(cartPrice())
                .address(address)
                .userName(userEntity.getName())
                .build();
        List<PizzaInOrderView> userCart = service.getUserCart(email);
        res.setPatternViewList(pizzaInOrderConvertor.convert(userCart));
        for (Order order : all) {
            if (order.getUserEntity().equals(userEntity)) {
                order.setStatus(OrderStatusEnum.ORDERED);
                order.setAddress(address);
                orderRepository.save(order);
            }
        }

        return res;
    }

    private Double cartPrice() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PizzaInOrder> cartByUserEmail = pizzaInOrderRepository.getCartByUserEmail(email);
        double price = 0;
        for (PizzaInOrder pizzaInOrder : cartByUserEmail) {
            price += pizzaInOrder.getPrice();
        }
        return price;
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


    public List<FullOrderView> getAllOrders(){
        List<Order> orders = orderRepository.findByStatusIsNotLike(OrderStatusEnum.IN_CART);
        return orderConvertor.convert(orders);
    }
}
