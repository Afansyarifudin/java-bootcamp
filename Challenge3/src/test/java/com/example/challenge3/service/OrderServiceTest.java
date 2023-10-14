package com.example.challenge3.service;

import com.example.challenge3.model.ListFood;
import com.example.challenge3.model.OrderFood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderServiceTest {
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void testAddOrder() {
        OrderFood order = new OrderFood(new ListFood(1, "Nasi Padang", 1000), 3, "Pedes");
        orderService.addOrder(order);

        assertEquals(1, orderService.getOrderFood().size());

    }

    @Test
    public void testFindExistingOrder2() {
        OrderFood order = new OrderFood(new ListFood(1, "Bakmi Godog", 10000), 10, "");
        orderService.addOrder(order);

        OrderFood foundOrder = orderService.findExistingOrder2(order.getName());
        assertEquals(order, foundOrder);
    }

    @Test
    public void testFindNonExistingOrder2() {
        OrderFood foundOrder = orderService.findExistingOrder2("Bakmi Maknyus");
        assertNull(foundOrder);
    }

}
