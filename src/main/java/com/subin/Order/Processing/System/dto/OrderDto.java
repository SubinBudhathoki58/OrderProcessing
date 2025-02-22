package com.subin.Order.Processing.System.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotBlank(message = "Name should not be blank")
    private String customerName;

    @NotBlank(message = "product should not be empty")
    private String product;

    @Min(value = 50,message = "Product price should be above 50")
    private double price;
}
