package com.example.springwebrest_ch5.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class OrderRequestDto {
    private String order_time;
    private String destination_address;
    private boolean completed;
    private UUID userId;
}
