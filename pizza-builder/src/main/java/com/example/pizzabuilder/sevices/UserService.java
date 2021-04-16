package com.example.pizzabuilder.sevices;



import com.example.pizzabuilder.convertors.UserConvertor;
import com.example.pizzabuilder.criteria.Criteria;
import com.example.pizzabuilder.criteria.UserCriteria;
import com.example.pizzabuilder.enums.RolesEnum;
import com.example.pizzabuilder.exceptions.EntityNotExistsException;
import com.example.pizzabuilder.exceptions.WrongRestrictionException;
import com.example.pizzabuilder.model.Address;
import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.view.UserViewSignUp;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConvertor userConvertor;

    public Criteria<UserEntity> parse(String restrict) throws WrongRestrictionException {
        return new UserCriteria(restrict);
    }

    @Transactional
    public Optional<UserEntity> existByEmail(@NotNull final String email) {
        return userRepository.findByEmail(email);
    }
    @Transactional
    public Optional<UserEntity> findByUUID(@NotNull final UUID uuid) {
        return userRepository.findByUuid(uuid);
    }

    public UserEntity saveNewUser(UserViewSignUp newUser) {
        UserEntity userEntity = userConvertor.convert(newUser);
        userEntity.setRoleId(RolesEnum.USER.ordinal());
        return userRepository.save(userEntity);
    }

    public UserEntity setAddress(UUID userId, Address address) throws Exception{
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent())
            throw new Exception("e");
        UserEntity user = userOptional.get();
        user.setAddress(address);
        return userRepository.saveAndFlush(user);
    }
    @Transactional
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
    @Transactional
    public UserEntity updateUser(UserViewSignUp userViewSignUp) throws Exception{
        Optional<UserEntity> userOptional = userRepository.findByEmail(userViewSignUp.getEmail());
        if(!userOptional.isPresent())
            throw new EntityNotExistsException(UserEntity.class, userViewSignUp.getEmail());
        UserEntity user = userOptional.get();
        user.setName(userViewSignUp.getName());
        user.setPhone(userViewSignUp.getPhone());
        user.setAddress(userViewSignUp.getAddress());
        return userRepository.saveAndFlush(user);
    }
    @Transactional
    public UserEntity setPassword(UUID uuid, String hashed_password) throws Exception{
        Optional<UserEntity> userOptional = userRepository.findById(uuid);
        if(!userOptional.isPresent())
            throw new Exception("e");
        UserEntity user = userOptional.get();
        user.setHashed_password(hashed_password);
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public UserEntity addPizzaPattern(UUID uuid, PizzaPattern pizzaPattern) throws Exception{
        Optional<UserEntity> userOptional = userRepository.findById(uuid);
        if(!userOptional.isPresent())
            throw new Exception("e");
        UserEntity user = userOptional.get();
        List<PizzaPattern> pizzaPatterns = user.getPizzaPatterns();
        pizzaPatterns.add(pizzaPattern);
        return userRepository.saveAndFlush(user);

    }
}
