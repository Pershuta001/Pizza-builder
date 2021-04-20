package com.example.pizzabuilder.model;

import com.example.pizzabuilder.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "uuid", updatable = false, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = false)
    private UserEntity userEntity;

    @OneToMany
    private List<PizzaInOrder> pizzaInOrders;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "date_time", nullable = false)
    private Date dataTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatusEnum status;

    @Embedded
    private Address address;


}
