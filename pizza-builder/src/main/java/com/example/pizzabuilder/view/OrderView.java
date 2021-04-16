package com.example.pizzabuilder.view;

import com.example.pizzabuilder.enums.OrderStatusEnum;
import com.example.pizzabuilder.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderView {
    private UUID uuid;

    private Double totalPrice;

    private Date dataTime;

    private OrderStatusEnum status;

    private Address address;
}
