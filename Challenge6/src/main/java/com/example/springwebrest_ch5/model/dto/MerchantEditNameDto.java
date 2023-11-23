package com.example.springwebrest_ch5.model.dto;
import jakarta.validation.constraints.Max;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
public class MerchantEditNameDto {
    @Size(min = 5, max = 15)
    @NotEmpty
    private String name;
}
