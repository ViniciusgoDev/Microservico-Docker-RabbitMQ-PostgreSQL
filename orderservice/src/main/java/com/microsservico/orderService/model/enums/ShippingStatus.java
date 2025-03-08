package com.microsservico.orderService.model.enums;

public enum ShippingStatus {

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
