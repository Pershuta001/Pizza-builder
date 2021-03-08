package com.example.pizzabuilder.settings.auth;

import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(login);
        if (!userEntityOptional.isPresent()) {
            throw new UsernameNotFoundException("User with login: " + login + " not found.");
        }
        UserEntity user = userEntityOptional.get();
        return new User(user.getEmail(),
                user.getHashed_password(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }
}
