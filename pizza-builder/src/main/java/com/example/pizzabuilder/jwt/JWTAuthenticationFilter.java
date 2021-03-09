package com.example.pizzabuilder.jwt;

import com.example.pizzabuilder.settings.auth.ApplicationUserService;
import com.example.pizzabuilder.view.UserViewLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UserViewLogin userViewLogin = new ObjectMapper().readValue(request.getInputStream(), UserViewLogin.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userViewLogin.getEmail(),
                    userViewLogin.getPassword()
            );
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
          throw new RuntimeException("User not authenticated");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {


        response.addHeader("Authorization",applicationUserService.generateToken(authResult.getName(),authResult.getAuthorities()));
    }


}
