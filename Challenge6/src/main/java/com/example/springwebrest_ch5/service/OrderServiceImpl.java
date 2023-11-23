package com.example.springwebrest_ch5.service;

import com.example.springwebrest_ch5.model.Order;
import com.example.springwebrest_ch5.model.User;
import com.example.springwebrest_ch5.model.dto.OrderRequestDto;
import com.example.springwebrest_ch5.repository.OrderRepository;
import com.example.springwebrest_ch5.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public Order create(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setOrder_time(orderRequestDto.getOrder_time());
        order.setDestination_address(orderRequestDto.getDestination_address());
        order.setCompleted(false);

        User user = userRepository.findById(orderRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User Id " + orderRequestDto.getUserId()+" Not Found"));
        order.setUser(user);
        return orderRepository.save(order);
    }

    @Override
    public Order checkout(UUID orderId) {
        Order order = getOrderById(orderId);
        order.setCompleted(true);
        return orderRepository.save(order);

    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
    }


    @Override
    public Optional<Order> getById(UUID id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            logger.info("There is no Order available");
            throw new RuntimeException("Order not found");
        }
        return orderOptional;
    }

    @Override
    public void delete(UUID id) {
        Optional<Order> orderOptional = getById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            LocalDateTime currentDate = LocalDateTime.now();
            order.setDeletedDate(currentDate);
            orderRepository.save(order);
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order Not Found");
        }
    }
}
