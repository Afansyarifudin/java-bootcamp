package com.example.springwebrest_ch5.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class ProductRequestDto {
    private String name;
    private long price;
    private UUID merchantId;
}
