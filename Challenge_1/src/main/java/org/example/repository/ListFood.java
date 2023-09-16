package org.example.repository;

public class ListFood {

    // Only accessed in this class
    private long id;
    private String name;
    private long price;

    // Getter Method to access (read-only) to private fields
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    // Constructor to initialize object of ListFood
    public ListFood (long id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
