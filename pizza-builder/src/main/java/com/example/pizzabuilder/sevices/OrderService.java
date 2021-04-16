package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.enums.OrderStatusEnum;
import com.example.pizzabuilder.enums.RolesEnum;
import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.repositories.OrderRepository;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.view.OrderView;
import com.example.pizzabuilder.view.UserViewSignUp;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    @Transactional
    public Order setStatus(UUID orderId, OrderStatusEnum status) throws Exception{
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(!orderOptional.isPresent())
            throw new Exception("e");
        Order order = orderOptional.get();
        order.setStatus(status);
        return orderRepository.saveAndFlush(order);
    }
    @Transactional
    public List<Order> getByStatus(OrderStatusEnum status){
        return orderRepository.findByStatus(status);
    }
    List<Order> getUserCart(UserViewSignUp userViewSignUp) throws EntityNotExistsException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(userViewSignUp.getEmail());
        if(!userEntity.isPresent())
            throw new EntityNotExistsException(UserEntity.class, userViewSignUp.getEmail());;
    return orderRepository.findByStatusAndUserEntity(OrderStatusEnum.IN_CART, userEntity.get());
    }

    @Transactional
    Order updateOrder(OrderView orderView) throws EntityNotExistsException {
        Optional<Order> optionalOrder = orderRepository.findByUuid(orderView.getUuid());
        if(!optionalOrder.isPresent())
            throw new EntityNotExistsException(UserEntity.class, orderView.getUuid());
        Order order = optionalOrder.get();
        //TODO how to count total price
        order.setTotalPrice(orderView.getTotalPrice());
        order.setDataTime(orderView.getDataTime());
        order.setStatus(orderView.getStatus());
        order.setAddress(orderView.getAddress());
        return orderRepository.saveAndFlush(order);


    }

    public Order saveNewOrder(OrderView newOrder) {
        Order order
        UserEntity userEntity = userConvertor.convert(newUser);
        userEntity.setRoleId(RolesEnum.USER.ordinal());
        return orderRepository.save(order);
    }
    //TODO exceptions timeFinding
    /*
    getByDate criteria
     */
}
