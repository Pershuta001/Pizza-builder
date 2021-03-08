package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.repositories.UserRepository;
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

    @Transactional
    public Optional<UserEntity> existByEmail(@NotNull final String email){
        return userRepository.findByEmail(email);
    }
    public List<UserEntity> getAll(){
     return null;
    }

}
