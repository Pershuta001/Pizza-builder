package com.example.pizzabuilder.settings.jwt;

import com.example.pizzabuilder.settings.auth.ApplicationUserService;
import com.example.pizzabuilder.sevices.UserService;
import com.example.pizzabuilder.view.UserViewLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UserViewLogin userViewLogin = new ObjectMapper().readValue(request.getInputStream(), UserViewLogin.class);
            log.info("New attempt to authenticate: " + userViewLogin);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userViewLogin.getEmail(),
                    userViewLogin.getPassword()
            );
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            logger.error("User not authenticated");
            throw new RuntimeException("User not authenticated");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {


        response.addHeader(
                "Authorization",
                applicationUserService.generateToken(
                        authResult.getName(),
                        authResult.getAuthorities()));
        try {

            response
                    .getWriter()
                    .write(applicationUserService.responseUser( authResult.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Authentication for {} was success",authResult.getName());
    }
}
