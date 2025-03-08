package com.microsservico.notificationService.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.microsservico.notificationService.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderDto(
        UUID id,
        UUID customerId,
        UUID storeId,
        BigDecimal totalPrice,
        String deliveryAddress,
        String pickupAddress,
        OrderStatus shippingStatus,
        Instant createdAt
//        List<Product> products

) {
}
