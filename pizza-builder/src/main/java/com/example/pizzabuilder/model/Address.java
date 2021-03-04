package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Address implements Serializable {
    @Column(name="city", nullable = false)
    private String city;

    @Column(name="street", nullable = false)
    private String street;

    @Column(name="build", nullable = false)
    private Integer build;

    @Column(name="flat")
    private Integer flat;
}
