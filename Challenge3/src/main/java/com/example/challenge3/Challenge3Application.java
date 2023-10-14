package com.example.challenge3;

import com.example.challenge3.controller.CashierController;
import com.example.challenge3.service.FoodService;
import com.example.challenge3.service.OrderService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Challenge3Application {

    public static void main(String[] args) {
//        SpringApplication.run(Challenge3Application.class, args);
        FoodService foodService = new FoodService();
        OrderService orderService = new OrderService();

        // Instantiate class CashierController
        CashierController cashierController = new CashierController(foodService, orderService);
        cashierController.run();
    }
}