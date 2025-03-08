package com.microsservico.notificationService.service;

import com.microsservico.notificationService.eexception.NotificationException;
import com.microsservico.notificationService.model.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {


    public void sendNotification(OrderDto orderDto) {
        try {

            log.info("Notification sent successfully - Order ID: {}, Customer ID: {}",
                    orderDto.customerId(),
                    orderDto.id());

        } catch (Exception e) {

            throw new NotificationException("Failed to send notification");
        }
    }
}