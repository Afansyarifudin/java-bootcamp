package com.example.springwebrest_ch5.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class OrderDetailRequestDto {
    private UUID orderId;
    private UUID productId;
    private int quantity;
}
