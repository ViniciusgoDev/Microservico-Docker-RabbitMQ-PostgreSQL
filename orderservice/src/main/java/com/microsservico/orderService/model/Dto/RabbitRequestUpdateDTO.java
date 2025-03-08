package com.microsservico.orderService.model.Dto;

import com.microsservico.orderService.model.enums.ShippingStatus;

import java.time.Instant;
import java.util.UUID;

public record RabbitRequestUpdateDTO(
         UUID orderId,
         Long trackingCode,
         ShippingStatus shippingStatus,
         Instant updatedAt
) {
}
