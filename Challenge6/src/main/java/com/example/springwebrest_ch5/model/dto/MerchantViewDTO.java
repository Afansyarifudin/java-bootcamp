package com.example.springwebrest_ch5.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class MerchantViewDTO {
    private String name;
    private String location;
    private boolean open;
}
