package com.microsservico.orderService.model.Dto;

import com.microsservico.orderService.model.Product;
import com.microsservico.orderService.model.enums.ShippingStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


public record OrderRequestDTO(

        @NotNull
        UUID customerId,

        @NotNull
        UUID storeId,


        @NotNull
        BigDecimal totalPrice,

        @NotEmpty
        List<Product> products,

        ShippingStatus status,

        BigDecimal discount,

        @NotBlank
        String pickupAddress,

        @NotBlank
        String deliveryAddress

) {
}
