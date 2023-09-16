package org.example.service;

import lombok.Getter;
import org.example.model.OrderFood;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderService {
    private List<OrderFood> orderFood;

    // Constructor
    public OrderService() {
        this.orderFood = new ArrayList<>();
    }

    // Add order
    public void addOrder(OrderFood order) {
        orderFood.add(order);
    }

    // Find existing order
    public OrderFood findExistingOrder(String foodName) {
        for (OrderFood existingOrder : orderFood) {
            if (existingOrder.getName().equalsIgnoreCase(foodName)) {
                return existingOrder;
            }
        }
        return null;
    }
}
