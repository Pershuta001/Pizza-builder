package com.example.pizzabuilder.settings.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtConfig {

    public final String SECRET_KEY;
    public final String TOKEN_PREFIX;
    public final Integer EXPIRATION_DAY;

    public JwtConfig( @Value("${security.jwt.secret.key}") String secret_key,
                      @Value("${security.jwt.token.prefix}") String tokenPrefix,
                      @Value("${security.jwt.token.expiration_day}") Integer expirationDay) {
        SECRET_KEY = secret_key;
        TOKEN_PREFIX = tokenPrefix;
        EXPIRATION_DAY = expirationDay;
    }

    @Bean
    public SecretKey signingSecretKey(){
       return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
