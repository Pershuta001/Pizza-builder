package com.example.pizzabuilder.convertors;

import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.view.UserViewSignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConvertor {

    private final PasswordEncoder bCryptPasswordEncoder;

    public UserEntity convert( UserViewSignUp userViewSignUp){
       return UserEntity.builder()
               .email(userViewSignUp.getEmail())
               .name(userViewSignUp.getName())
               .address(userViewSignUp.getAddress())
               .hashed_password(bCryptPasswordEncoder.encode(userViewSignUp.getPassword()))
               .phone(userViewSignUp.getPhone())
               .build();
    }
}
