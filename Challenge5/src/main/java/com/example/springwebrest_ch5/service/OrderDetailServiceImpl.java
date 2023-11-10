package com.example.springwebrest_ch5.service;

import com.example.springwebrest_ch5.model.Order;
import com.example.springwebrest_ch5.model.OrderDetail;
import com.example.springwebrest_ch5.model.Product;
import com.example.springwebrest_ch5.model.dto.OrderDetailRequestDto;
import com.example.springwebrest_ch5.repository.OrderDetailRepository;
import com.example.springwebrest_ch5.repository.OrderRepository;
import com.example.springwebrest_ch5.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public OrderDetail create(OrderDetailRequestDto orderDetailRequestDto) {
        OrderDetail orderDetail = new OrderDetail();
        Order order = orderRepository.findById(orderDetailRequestDto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order Id" + orderDetailRequestDto.getOrderId() + " Not found"));
        orderDetail.setOrder(order);
        Product product = productRepository.findById(orderDetailRequestDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product Id" + orderDetailRequestDto.getProductId() + " Not found"));
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailRequestDto.getQuantity())
                .setTotal_price(product.getPrice() * orderDetailRequestDto.getQuantity());

        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getAll() {
        return null;
    }

    @Override
    public OrderDetail getOrderDetailById(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
