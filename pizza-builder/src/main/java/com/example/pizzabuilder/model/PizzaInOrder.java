package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pizza_in_order")
public class PizzaInOrder {
    @EmbeddedId
    private PizzaInOrderId id;

    @ManyToOne
    @JoinColumn(name = "pizza_pattern_uuid", insertable = false, updatable = false)
    private PizzaPattern pizzaPattern;

    @ManyToOne
    @JoinColumn(name = "orders_uuid", insertable = false, updatable = false)
    private Order order;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

}
