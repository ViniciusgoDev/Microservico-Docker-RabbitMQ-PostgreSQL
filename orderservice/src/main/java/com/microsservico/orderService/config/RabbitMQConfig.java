package com.microsservico.orderService.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_EXCHANGE = "orderExchange";

    public static final String ORDER_CREATED_QUEUE = "orderCreatedQueue";
    public static final String ORDER_UPDATED_QUEUE = "orderUpdatedQueue";
    public static final String ORDER_NOTIFICATION_QUEUE = "orderNotificationQueue";

    public static final String ORDER_CREATED_KEY = "order.created";
    public static final String ORDER_NOTIFICATION_KEY = "order.notification";

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE, true, false);
    }

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(ORDER_CREATED_QUEUE, true);
    }


    @Bean
    public Queue orderNotificationQueue() {
        return new Queue(ORDER_NOTIFICATION_QUEUE, true);
    }


    @Bean
    public Binding bindingOrderCreated(Queue orderCreatedQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderCreatedQueue).to(orderExchange).with(ORDER_CREATED_KEY);
    }


    @Bean
    public Binding bindingOrderNotificationQueue(Queue orderNotificationQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderNotificationQueue).to(orderExchange).with(ORDER_NOTIFICATION_KEY);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
