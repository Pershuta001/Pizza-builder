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
public class OrderId implements Serializable {
    @Column(name="pizza_uuid", nullable = false)
    private UUID pizzaUUID;

    @Column(name="user_uuid", nullable = false)
    private UUID userUUID;


}
