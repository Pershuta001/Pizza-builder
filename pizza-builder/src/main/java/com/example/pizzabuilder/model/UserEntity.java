package com.example.pizzabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "roleId")
    private Integer roleId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_uuid")
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_pattern_uuid")
    private List<PizzaPattern> pizzaPatterns;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @Column(name = "hashed_password", nullable = false)
    private String hashed_password;

    @Lob
    @Column(name="photo")
    private byte [] photo;

}
