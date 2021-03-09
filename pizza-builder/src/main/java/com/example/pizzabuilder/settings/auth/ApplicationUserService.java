package com.example.pizzabuilder.settings.auth;

import com.example.pizzabuilder.enums.RolesEnum;
import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.repositories.UserRepository;
import com.example.pizzabuilder.settings.jwt.JwtConfig;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
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
public class ApplicationUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(login);
        if (!userEntityOptional.isPresent()) {
            throw new UsernameNotFoundException("User with login: " + login + " not found.");
        }
        UserEntity user = userEntityOptional.get();
        Set<SimpleGrantedAuthority> authorities = RolesEnum.values()[user.getRole()].getGrantedAuthorities();
        return new User(user.getEmail(),
                user.getHashed_password(),
                true,
                true,
                true,
                true,
                authorities
        );
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
