package com.cdis.microservice.example.catalog.configurations;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    /*
    @Bean
    public TopicExchange catalogExchange(@Value("${catalog.exchange}") final String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    */
}
