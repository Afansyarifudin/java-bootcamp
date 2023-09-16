package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListFood {
    private long id;
    private String name;
    private long price;
}
