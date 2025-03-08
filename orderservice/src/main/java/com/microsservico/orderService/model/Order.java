package com.microsservico.orderService.model;


import com.microsservico.orderService.model.enums.ShippingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Table(name = "Orders")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID customerId;

    @Column(nullable = false)
    private UUID storeId;

    @Column(nullable = true)
    private BigDecimal totalPrice;

    @Column
    private BigDecimal discount;

    @Column
    private Long trackingCode;


    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;

    @Column(name = "pickupAddress", nullable = false)
    private String pickupAddress;

    @Column(name = "deliveryAddress", nullable = false)
    private String deliveryAddress;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @OneToMany
    private List<Product> products;





}
