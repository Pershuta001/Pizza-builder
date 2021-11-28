package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.convertors.PizzaInOrderConvertor;
import com.example.pizzabuilder.enums.OrderStatusEnum;
import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.model.*;
import com.example.pizzabuilder.repositories.OrderRepository;
import com.example.pizzabuilder.repositories.PizzaInOrderRepository;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.utils.Utils;
import com.example.pizzabuilder.view.PizzaInOrderView;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PizzaInOrderService {
    private final PizzaInOrderRepository pizzaInOrderRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PizzaPatternService pizzaPatternService;
    private final PizzaInOrderConvertor pizzaInOrderConvertor;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public PizzaInOrder saveNewPizzaInOrder(PizzaInOrderView pizzaInOrderView) throws Exception {
        PizzaInOrder pizzaInOrder = pizzaInOrderConvertor.convert(pizzaInOrderView);
        pizzaInOrder.setPrice(pizzaInOrder.getQuantity() * Utils.countPatternPrice(pizzaPatternService.getById(pizzaInOrder.getId().getPizzaPatternUUID())));
        return pizzaInOrderRepository.save(pizzaInOrder);
    }

    @Transactional
    public PizzaInOrder setQuantity(PizzaInOrderId inOrderId, Integer quantity) throws Exception {
        Optional<PizzaInOrder> pizzaInOrderOptional = pizzaInOrderRepository.findById(inOrderId);
        if (!pizzaInOrderOptional.isPresent())
            throw new Exception("No pizza in order with id " + inOrderId.toString());
        PizzaInOrder pizzaInOrder = pizzaInOrderOptional.get();
        pizzaInOrder.setQuantity(quantity);
        return pizzaInOrderRepository.saveAndFlush(pizzaInOrder);
    }


    @Transactional
    public void delete(UUID pattern, Integer size) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByEmail(email).get();
        List<Order> orders = orderRepository.findByStatusAndUserEntity(OrderStatusEnum.IN_CART, user);
        if (orders.isEmpty()) {
            return;
        }
        Order order=orders.get(0);
        for (PizzaInOrder pizzaInOrder : order.getPizzaInOrders()) {
            if (pizzaInOrder.getId().getPizzaPatternUUID().equals(pattern) && pizzaInOrder.getId().getPizzaSize().equals(size)) {
                pizzaInOrderRepository.deleteById(pizzaInOrder.getId());
            }
        }
    }

    @SneakyThrows
    public String responsePizzaInOrder(PizzaInOrder pizzaInOrder) {
        String res = "{";
        res += String.format("\"orderId\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getId().getOrdersUUID()));
        res += String.format("\"totalPrice\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getPrice()));
        res += String.format("\"quantity\": \"%s\",", objectMapper.writeValueAsString(pizzaInOrder.getQuantity()));
        res += "\"size\":" + objectMapper.writeValueAsString(pizzaInOrder.getId().getPizzaSize()) + "}";
        return res;
    }

    @Transactional
    public PizzaInOrder updatePizzaInOrder(PizzaInOrderView pizzaInOrderView) throws Exception {
        PizzaInOrder pizzaInOrder = pizzaInOrderConvertor.convert(pizzaInOrderView);
        Optional<PizzaInOrder> pizzaInOrderOptional = pizzaInOrderRepository.findById(pizzaInOrder.getId());
        if (!pizzaInOrderOptional.isPresent())
            throw new EntityNotExistsException(UserEntity.class, pizzaInOrder.getId());
        PizzaInOrder pizzaInOrderDB = pizzaInOrderOptional.get();
        pizzaInOrderDB.setQuantity(pizzaInOrder.getQuantity());
        pizzaInOrder.setPrice(pizzaInOrder.getQuantity() * Utils.countPatternPrice(pizzaPatternService.getById(pizzaInOrderDB.getId().getPizzaPatternUUID())));
        return pizzaInOrderRepository.saveAndFlush(pizzaInOrderDB);
    }

    private PizzaInOrderView contains(List<PizzaInOrderView> pizzaInOrders, PizzaInOrder pizzaInOrder) {
        for (PizzaInOrderView pizza : pizzaInOrders
        ) {
            if (pizza.getPizzaPatternUUID().equals(pizzaInOrder.getId().getPizzaPatternUUID()) &&
                    pizza.getPizzaSize().equals(pizzaInOrder.getId().getPizzaSize()))
                return pizza;

        }
        return null;
    }

    @Transactional
    public List<PizzaInOrderView> getUserCart(String email) {
        System.out.println(email);
        List<PizzaInOrder> pizzaInOrder = pizzaInOrderRepository.getCartByUserEmail(email);
        List<PizzaInOrderView> res = new ArrayList<>();

        for (PizzaInOrder pizza : pizzaInOrder) {
            PizzaInOrderView p = contains(res, pizza);
            if (p != null) {
                p.setQuantity(p.getQuantity() + pizza.getQuantity());
                p.setPrice(p.getPrice() * p.getQuantity());
            } else {
                res.add(pizzaInOrderConvertor.convert(pizza));
            }
        }

        return res;
    }

    public PizzaInOrder increment(String email, UUID patternUuid, Integer size, Integer val) {
        List<PizzaInOrder> pizzaInOrder = pizzaInOrderRepository.getCartByUserEmail(email);
        for (PizzaInOrder pizza : pizzaInOrder) {
            if (pizza.getId().getPizzaSize().equals(size) &&
                    pizza.getId().getPizzaPatternUUID().equals(patternUuid)) {
                pizza.setQuantity(pizza.getQuantity() + val);
                pizzaInOrderRepository.save(pizza);
                return pizza;
            }

        }
        return null;
    }
}
