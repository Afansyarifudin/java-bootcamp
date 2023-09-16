package org.example.service;

import lombok.Getter;
import org.example.model.ListFood;

import java.util.List;

@Getter
public class FoodService {
    private List<ListFood> listFood;

    // Constructor
    public FoodService() {
        listFood = List.of(
                new ListFood(1, "Nasi Goreng", 15000),
                new ListFood(2, "Mie Goreng", 13000),
                new ListFood(3, "Nasi + Ayam", 18000),
                new ListFood(4, "Es Teh Manis", 3000),
                new ListFood(5, "Es Jeruk", 5000)
        );
    }
}
