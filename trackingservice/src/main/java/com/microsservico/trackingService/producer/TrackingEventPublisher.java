package com.microsservico.trackingService.producer;

import com.microsservico.trackingService.config.RabbitMQConfig;
import com.microsservico.trackingService.model.TrackingEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TrackingEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void sendTrackingUpdate(TrackingEvent event){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ORDER_UPDATED_ROUTING_KEY, event);
    }


}
