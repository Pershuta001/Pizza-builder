package com.example.pizzabuilder.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Data
@Embeddable
public class PizzaInOrderId implements Serializable {
    @Column(name="pizza_pattern_uuid", nullable = false)
    private UUID pizzaPatternUUID;

    @Column(name="orders_uuid",nullable = false)
    private Integer ordersUUID;

    @Column(name = "pizza_size", nullable = false)
    private Integer pizzaSize;
}
