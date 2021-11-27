package com.example.pizzabuilder.settings.auth;

import com.example.pizzabuilder.enums.RolesEnum;
import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.settings.jwt.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;
    private UserEntity user;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(login);
        if (!userEntityOptional.isPresent()) {
            log.error("User with login: {} not found.",login);
            throw new UsernameNotFoundException("User with login: " + login + " not found.");
        }
        user = userEntityOptional.get();
        Set<SimpleGrantedAuthority> authorities = RolesEnum.values()[user.getRoleId()].getGrantedAuthorities();
        return new User(user.getEmail(),
                user.getHashed_password(),
                true,
                true,
                true,
                true,
                authorities
        );
    }

    @SneakyThrows
    public String responseUser(){
        String res = "{";
        res += String.format("\"uuid\": \"%s\",", user.getUuid());
       res += String.format("\"name\": \"%s\",", user.getName());
       res += String.format("\"email\": \"%s\",", user.getEmail());
       res += String.format("\"phone\": \"%s\",", user.getPhone());
       res += String.format("\"role\": \"%s\",", RolesEnum.values()[user.getRoleId()]);
       res += "\"address\":"+new ObjectMapper().writeValueAsString(user.getAddress())+"}";
       return res;
    }
    @SneakyThrows
    public String responseUser(UserEntity user){
        String res = "{";
        res += String.format("\"uuid\": \"%s\",", user.getUuid());
        res += String.format("\"name\": \"%s\",", user.getName());
        res += String.format("\"email\": \"%s\",", user.getEmail());
        res += String.format("\"phone\": \"%s\",", user.getPhone());
        res += String.format("\"role\": \"%s\",", RolesEnum.values()[user.getRoleId()]);
        res += "\"address\":"+new ObjectMapper().writeValueAsString(user.getAddress())+"}";
        return res;
    }

    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return jwtConfig.TOKEN_PREFIX + Jwts.builder()
                .setSubject(username)
                .claim("authorities", authorities)
                .setExpiration(Date.valueOf((LocalDate.now().plusDays(jwtConfig.EXPIRATION_DAY))))
                .signWith(jwtConfig.signingSecretKey())
                .compact();
    }
}
