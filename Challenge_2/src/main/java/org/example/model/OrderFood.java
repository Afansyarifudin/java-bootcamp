package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderFood {
    private ListFood listFood;
    private @Setter int amount;
    private @Setter String description;

    public OrderFood(ListFood listFood, int amount, String description) {
        this.listFood = listFood;
        this.amount = amount;
        this.description = description;
    }

    // Getter methods for name and price using the ListFood reference
    public String getName() {
        return listFood.getName();
    }

    public long getPrice() {
        return listFood.getPrice();
    }

    public void setPrice(long price) {
        this.listFood.setPrice(price);
    }
}
