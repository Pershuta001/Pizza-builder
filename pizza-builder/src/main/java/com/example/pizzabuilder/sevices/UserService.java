package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.model.Address;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.model.User;
import com.example.pizzabuilder.repositories.UserRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User setAddress(UUID userId, Address address) throws Exception{
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent())
            throw new Exception("e");
        User user = userOptional.get();
        user.setAddress(address);
        return userRepository.saveAndFlush(user);
    }
    @Transactional
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Transactional
    public User updateUser(UUID uuid, String name, String phone, byte [] photo) throws Exception{
        Optional<User> userOptional = userRepository.findById(uuid);
        if(!userOptional.isPresent())
            throw new Exception("e");
        User user = userOptional.get();
        user.setName(name);
        user.setPhone(phone);
        user.setPhoto(photo);
        return userRepository.saveAndFlush(user);
    }
    @Transactional
    public User setPassword(UUID uuid, String hashed_password) throws Exception{
        Optional<User> userOptional = userRepository.findById(uuid);
        if(!userOptional.isPresent())
            throw new Exception("e");
        User user = userOptional.get();
        user.setHashed_password(hashed_password);
        return userRepository.saveAndFlush(user);
    }
    @Transactional
    public User setEmail(UUID uuid, String email) throws Exception{
        Optional<User> userOptional = userRepository.findById(uuid);
        if(!userOptional.isPresent())
            throw new Exception("e");
        if(userRepository.findByEmail(email).isPresent())
            throw new Exception("e");
        User user = userOptional.get();
        user.setEmail(email);
        return userRepository.saveAndFlush(user);
    }
    @Transactional
    public User addPizzaPattern(UUID uuid, PizzaPattern pizzaPattern) throws Exception{
        Optional<User> userOptional = userRepository.findById(uuid);
        if(!userOptional.isPresent())
            throw new Exception("e");
        User user = userOptional.get();
        List<PizzaPattern> pizzaPatterns = user.getPizzaPatterns();
        pizzaPatterns.add(pizzaPattern);
        return userRepository.saveAndFlush(user);
    }


}
