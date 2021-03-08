package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.view.UserViewLogin;
import com.example.pizzabuilder.view.UserViewSignUp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    @ResponseBody
    @GetMapping()
    public ResponseEntity<UserViewLogin> getWithoutLogging(
    ) {
        ResponseEntity<UserViewLogin> userViewLoginResponseEntity = new ResponseEntity<UserViewLogin>(
                UserViewLogin.builder().email("email").password("password").build(),
                HttpStatus.OK);
        return userViewLoginResponseEntity;
    }


    @ResponseBody
    @PostMapping("doLogin")
    public ResponseEntity<UserViewLogin> doLogin(
            @RequestBody UserViewLogin userLogin
    ) {
        ResponseEntity<UserViewLogin> userViewLoginResponseEntity = new ResponseEntity<UserViewLogin>(
                userLogin,
                HttpStatus.OK);
        return userViewLoginResponseEntity;
    }

    @ResponseBody
    @PostMapping("/sign-up")
    public UserViewSignUp doSignUp(
            @RequestBody UserViewSignUp userViewSignUp
    ) {
        return userViewSignUp;
    }
}
