package com.cdis.microservice.example.basket.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * Configures RabbitMQ to use events in our application.
 */
@Configuration
@EnableRabbit
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

    // USER LISTENER BEANS
    @Bean
    public TopicExchange userExchange(@Value("${user.exchange}") final String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue userQueue(@Value("${user.queue}") final String queueName) {
        return new Queue(queueName, true);
    }

    @Bean
    Binding userBinding(final Queue userQueue, final TopicExchange userExchange,
                    @Value("${user.anything.routing-key}") final String routingKey) {
        return BindingBuilder.bind(userQueue).to(userExchange).with(routingKey);
    }

    // PRODUCT LISTENER BEANS
    @Bean
    public TopicExchange productExchange(@Value("${product.exchange}") final String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue productQueue(@Value("${product.queue}") final String queueName) {
        return new Queue(queueName, true);
    }

    @Bean
    Binding productBinding(final Queue productQueue, final TopicExchange productExchange,
                    @Value("${product.anything.routing-key}") final String routingKey) {
        return BindingBuilder.bind(productQueue).to(productExchange).with(routingKey);
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }


    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
