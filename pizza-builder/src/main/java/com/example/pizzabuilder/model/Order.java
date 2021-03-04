package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @EmbeddedId
    private OrderId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_uuid", nullable = false, updatable = false, insertable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_uuid",nullable = false, updatable = false, insertable = false)
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
