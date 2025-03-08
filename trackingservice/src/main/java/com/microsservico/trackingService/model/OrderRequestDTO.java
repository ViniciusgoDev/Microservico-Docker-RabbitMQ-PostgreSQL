package com.microsservico.trackingService.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


public record OrderRequestDTO(UUID id,
                              @Null
                              UUID customerId,
                              @Null
                              UUID storeId,
                              @Null
                              BigDecimal totalPrice,
                              @NotBlank
                              String deliveryAddress,
                              @NotBlank
                              String pickupAddress,
                              @Null
                              Status status,
                              @Null
                              Instant createdAt) {


}
