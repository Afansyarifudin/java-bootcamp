package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class OrderFood {
    private String name;
    private @Setter int amount;
    private @Setter long price;
}
