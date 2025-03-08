package com.microsservico.orderService.consumer;

import com.microsservico.orderService.model.Dto.RabbitRequestUpdateDTO;
import com.microsservico.orderService.model.Order;
import com.microsservico.orderService.repository.OrderRepository;
import com.microsservico.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderUpdatedEventConsumer {
    private final OrderRepository orderRepository;
    private final OrderService service;

    @Transactional
    @RabbitListener(queues = "orderUpdatedQueue")
    public void handleOrderUpdatedEvent(RabbitRequestUpdateDTO event) {
        log.info("Received order update event: {}", event);
        try {
            service.processOrderUpdate(event);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}

