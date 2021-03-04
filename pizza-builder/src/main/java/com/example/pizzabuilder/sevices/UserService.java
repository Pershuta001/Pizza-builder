package com.example.pizzabuilder.sevices;

import com.example.pizzabuilder.model.User;
import com.example.pizzabuilder.repositories.UserRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Optional<User> existByEmail(@NotNull final String email){
        return userRepository.findByEmail(email);
    }
    public List<User> getAll(){
     return null;
    }

}
