package com.example.shopbackend.dto;

import com.example.shopbackend.entity.Address;
import com.example.shopbackend.entity.Customer;
import com.example.shopbackend.entity.Order;
import com.example.shopbackend.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;
}
