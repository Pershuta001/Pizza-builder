package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.controllers.UserController;
import com.example.pizzabuilder.convertors.UserConvertor;
import com.example.pizzabuilder.enums.RolesEnum;
import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.view.UserViewSignUp;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConvertor userConvertor;

    @Transactional
    public Optional<UserEntity> existByEmail(@NotNull final String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity saveNewUser(UserViewSignUp newUser) {
        UserEntity userEntity = userConvertor.convert(newUser);
        userEntity.setRole(RolesEnum.USER.ordinal());
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getAll() {
        return null;

    }

}
