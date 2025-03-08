package com.microsservico.orderService.service;

import com.microsservico.orderService.mapper.OrderMapper;
import com.microsservico.orderService.model.Dto.OrderRequestDTO;
import com.microsservico.orderService.model.Dto.OrderResponseDTO;
import com.microsservico.orderService.model.Dto.RabbitRequestUpdateDTO;
import com.microsservico.orderService.model.Order;
import com.microsservico.orderService.model.Product;
import com.microsservico.orderService.model.enums.ShippingStatus;
import com.microsservico.orderService.producer.OrderMessageProducer;
import com.microsservico.orderService.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMessageProducer orderMessageProducer;

    private final OrderMapper mapper;

    public void create(OrderRequestDTO request) {
        Order order = Order.builder()
                .customerId(request.customerId())
                .storeId(request.storeId())
                .totalPrice(request.totalPrice())
                .products(request.products())
                .pickupAddress(request.pickupAddress())
                .deliveryAddress(request.deliveryAddress())
                .createdAt(Instant.now())
                .shippingStatus(ShippingStatus.PENDING)
                .build();
        Order savedOrder = orderRepository.save(order);
        log.info("Order created successfully id: {}", savedOrder.getId());

        OrderResponseDTO dto = mapper.toOrderResponseDTO(savedOrder);
        orderMessageProducer.publishOrderCreated(dto);
    }
    public OrderResponseDTO search(UUID id) {
        log.info("Searching for order data...");
        log.info("Searching for order data...");
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
       BigDecimal total = order.getProducts().stream()
               .map(Product::getPrice)
               .reduce(BigDecimal.ZERO, BigDecimal::add);

       order.setTotalPrice(total);

        log.info("Localized order data = {}", order);

       return ResponseEntity.ok(mapper.toOrderResponseDTO(order)).getBody();
    }

    @Transactional
    public void updateStatus(UUID id, ShippingStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Order not found with id"+ id));
        order.setShippingStatus(status);
    log.info("Order updated successfully");
    }

    public void processOrderUpdate(RabbitRequestUpdateDTO event) {
        Order order = orderRepository.findById(event.orderId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Order not found with ID: %d", event.orderId())
                ));
        order.setShippingStatus(event.shippingStatus());
        orderRepository.save(order);

        OrderResponseDTO orderResponseDTO = mapper.toOrderResponseDTO(order);
        log.info("Shipping notification sent for order: {}", event.orderId());

        orderMessageProducer.notifyAboutShippingUpdate(orderResponseDTO);

    }
}
