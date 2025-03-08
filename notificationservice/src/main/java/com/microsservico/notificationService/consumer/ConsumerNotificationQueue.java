package com.microsservico.notificationService.consumer;

import com.microsservico.notificationService.model.OrderDto;
import com.microsservico.notificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ConsumerNotificationQueue {

    private final NotificationService service;

    @RabbitListener(queues = "orderNotificationQueue")
    public void handleNotificationEvent(OrderDto orderDto){
        log.info(
                "Order received notification event: {}", orderDto);
        //eu tbm poderia fazer outras validaçoes aqui
        try {
            service.sendNotification(orderDto);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
