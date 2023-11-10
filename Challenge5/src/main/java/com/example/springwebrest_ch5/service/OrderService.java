package com.example.springwebrest_ch5.service;

import com.example.springwebrest_ch5.model.Order;
import com.example.springwebrest_ch5.model.dto.OrderRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Order create(OrderRequestDto orderRequestDto);

    Order checkout(UUID orderId);

    List<Order> getAll();

    Optional<Order> getById(UUID id);

    void delete(UUID id);

}
