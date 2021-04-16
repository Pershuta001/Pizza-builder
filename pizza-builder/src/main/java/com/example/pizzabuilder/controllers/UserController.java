package com.example.pizzabuilder.controllers;

import com.example.pizzabuilder.model.UserEntity;
import com.example.pizzabuilder.settings.auth.ApplicationUserService;
import com.example.pizzabuilder.sevices.UserService;
import com.example.pizzabuilder.view.UserViewSignUp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    private final ApplicationUserService applicationUserService;

    @ResponseBody
    @GetMapping("/exist")
    public Optional<UserEntity> existByEmail(
            @RequestParam String email
    ){
        return userService.existByEmail(email);
    }

    /**
     * {
     *     "name":"name",
     *     "email": "mail@mail.com",
     *     "phone": "9876543210",
     *     "address":{
     *         "city": "city",
     *         "street": "street",
     *         "build": 1,
     *         "flat": 1
     *     },
     *     "password":"password"
     * }
     * @param userViewSignUp
     * @return
     */
    @ResponseBody
    @PostMapping("/sign-up")
    public ResponseEntity<String> addNewUser(
            @RequestBody UserViewSignUp userViewSignUp
            ){
        UserEntity userEntity = userService.saveNewUser(userViewSignUp);
        HttpHeaders headers  = new HttpHeaders();
        UserDetails userDetails = applicationUserService.loadUserByUsername(userEntity.getEmail());
        headers.set("Authorization", applicationUserService.generateToken(userDetails.getUsername(), userDetails.getAuthorities()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(applicationUserService.responseUser());
    }

    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(
            @RequestBody UserViewSignUp userViewSignUp
    ) throws Exception {
        UserEntity userEntity = userService.updateUser(userViewSignUp);
        return ResponseEntity
                .ok()
                .body(applicationUserService.responseUser(userEntity));
    }
}
