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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    // Constructor to initialize object of Order class
    public OrderFood(String name, int amount, long price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
}
