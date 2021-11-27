package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class Address implements Serializable {
    @Column(name="city")
    private String city;

    @Column(name="street")
    private String street;

    @Column(name="build")
    private Integer build;

    @Column(name="flat")
    private Integer flat;
}
