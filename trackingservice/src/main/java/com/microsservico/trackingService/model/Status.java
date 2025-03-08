package com.microsservico.trackingService.model;

public enum Status {

    PENDING,
    PROCESSING,
    SHIPPED,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED,
    RETURNED,
    FAILED_DELIVERY;

}
