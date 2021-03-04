package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "Order")
@Table(name = "order")
public class Order {
    @EmbeddedId
    private OrderId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_uuid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pizza_uuid")
    private PizzaPattern pizzaPattern;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "date_time", nullable = false)
    private Date dataTime;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Embedded
    private Address address;


}
