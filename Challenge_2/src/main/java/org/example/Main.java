package org.example;

import org.example.controller.CashierController;
import org.example.service.FoodService;
import org.example.service.OrderService;

public class Main {
    public static void main(String[] args) {
        // Initialize data
        FoodService foodService = new FoodService();
        OrderService orderService = new OrderService();

        // Instantiate class CashierController
        CashierController cashierController = new CashierController(foodService, orderService);
        cashierController.run();
    }
}