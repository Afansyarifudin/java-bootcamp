package org.example;

import org.example.controller.CashierController;
import org.example.model.ListFood;
import org.example.service.FoodService;
import org.example.service.OrderService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize
        FoodService foodService = new FoodService();
        OrderService orderService = new OrderService();

        // Instantiate class CashierController
        CashierController cashierController = new CashierController(foodService, orderService);
        cashierController.run();

    }


}