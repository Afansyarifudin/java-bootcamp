package com.example.springwebrest_ch5.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ProductEditStockDto {
    @NotEmpty
    private long price;
}
