package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = false)
    private UserEntity userEntity;

    @OneToMany
    private List<PizzaInOrder> pizzaInOrders;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "date_time", nullable = false)
    private Date dataTime;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Embedded
    private Address address;


}
