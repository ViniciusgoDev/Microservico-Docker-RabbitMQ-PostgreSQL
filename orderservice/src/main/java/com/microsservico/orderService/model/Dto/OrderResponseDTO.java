package com.microsservico.orderService.model.Dto;

import com.microsservico.orderService.model.enums.ShippingStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        UUID customerId,
        UUID storeId,
        BigDecimal totalPrice,
        String deliveryAddress,
        String pickupAddress,
        ShippingStatus shippingStatus,
        Instant createdAt

) {
}
