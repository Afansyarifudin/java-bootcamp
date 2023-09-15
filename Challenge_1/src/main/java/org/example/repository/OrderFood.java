package org.example.repository;

public class OrderFood {

    // Only accessed in this class
    private String name;
    private int amount;
    private long price;

    // Getter method to access (read-only) private fields
    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public long getPrice() {
        return price;
    }

    // Constructor to initialize object of Order class
    public OrderFood(String name, int amount, long price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
}
