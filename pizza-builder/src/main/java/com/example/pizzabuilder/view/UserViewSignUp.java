package com.example.pizzabuilder.view;

import com.example.pizzabuilder.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserViewSignUp {
    private String name;
    private String email;
    private String phone;
    private Address address;
    private String password;
}
