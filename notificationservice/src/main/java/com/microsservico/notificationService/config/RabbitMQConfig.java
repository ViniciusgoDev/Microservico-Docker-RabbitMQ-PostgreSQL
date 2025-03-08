package com.microsservico.notificationService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_EXCHANGE = "orderExchange";

    public static final String ORDER_NOTIFICATION_QUEUE = "orderNotificationQueue";

    @Bean
    public Binding bindingOrderNotificationQueue(Queue orderNotificationQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderNotificationQueue).to(orderExchange).with("order.notification");
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE, true, false);
    }

    @Bean
    public Queue orderNotificationQueue() {
        return new Queue(ORDER_NOTIFICATION_QUEUE, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
