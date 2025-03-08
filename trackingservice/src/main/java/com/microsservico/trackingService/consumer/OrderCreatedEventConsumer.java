package com.microsservico.trackingService.consumer;

import com.microsservico.trackingService.model.OrderRequestDTO;
import com.microsservico.trackingService.model.TrackingEvent;
import com.microsservico.trackingService.service.TrackingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderCreatedEventConsumer {

    private final TrackingService trackingService;


    @RabbitListener(queues = "orderCreatedQueue")
    public void handleOrderCreatedEvent(@Valid OrderRequestDTO event) {
        log.info("New order registered... preparing transport: {}", event);
                trackingService.initiateTracking(event);
    }

}
