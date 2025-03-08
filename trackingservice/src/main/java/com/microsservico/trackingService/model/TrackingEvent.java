package com.microsservico.trackingService.model;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class TrackingEvent {
    private UUID orderId;
    private Long trackingCode;
    private String status;
    private Instant updatedAt;
}
