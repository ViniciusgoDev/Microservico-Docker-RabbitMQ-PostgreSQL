package com.microsservico.trackingService.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_CREATED_QUEUE = "orderCreatedQueue";
    public static final String ORDER_UPDATED_QUEUE = "orderUpdatedQueue";
    public static final String EXCHANGE_NAME = "orderExchange";
    public static final String ORDER_CREATED_ROUTING_KEY = "order.created";
    public static final String ORDER_UPDATED_ROUTING_KEY = "order.updated";

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(ORDER_CREATED_QUEUE, true);
    }

    @Bean
    public Queue orderUpdateQueue() {
        return new Queue(ORDER_UPDATED_QUEUE, true);
    }


    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }


    @Bean
    public Binding orderUpdatedBinding(Queue orderUpdateQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderUpdateQueue).to(orderExchange).with(ORDER_UPDATED_ROUTING_KEY);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
