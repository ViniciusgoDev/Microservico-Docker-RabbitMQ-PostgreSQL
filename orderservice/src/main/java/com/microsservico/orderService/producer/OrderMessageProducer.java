package com.microsservico.orderService.producer;

import com.microsservico.orderService.config.RabbitMQConfig;
import com.microsservico.orderService.model.Dto.OrderResponseDTO;
import com.microsservico.orderService.model.Dto.RabbitRequestUpdateDTO;
import com.microsservico.orderService.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishOrderCreated(Object orderEvent) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, RabbitMQConfig.ORDER_CREATED_KEY, orderEvent);
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, RabbitMQConfig.ORDER_NOTIFICATION_KEY, orderEvent);
        log.info(" Sent to 'order.created': {}", orderEvent);
    }


    public void notifyAboutShippingUpdate(OrderResponseDTO orderResponseDTO) {
        log.info("Sending processed order to queue: {}", orderResponseDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, RabbitMQConfig.ORDER_NOTIFICATION_KEY, orderResponseDTO);

    }
}
