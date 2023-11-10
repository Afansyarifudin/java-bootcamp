package com.example.springwebrest_ch5.service;

import com.example.springwebrest_ch5.model.OrderDetail;
import com.example.springwebrest_ch5.model.dto.OrderDetailRequestDto;

import java.util.List;
import java.util.UUID;

public interface OrderDetailService {
    OrderDetail create(OrderDetailRequestDto orderDetailRequestDto);
    List<OrderDetail> getAll();
    OrderDetail getOrderDetailById(UUID id);
    void delete(UUID id);
}
