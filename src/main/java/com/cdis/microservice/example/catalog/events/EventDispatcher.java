package com.cdis.microservice.example.catalog.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {
    /*private RabbitTemplate rabbitTemplate;

    // The exchange to use to send anything related to Multiplication
    private String catalogExchange;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${catalog.exchange}") final String multiplicationExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.catalogExchange = multiplicationExchange;
    }

    public void send(final CatalogSolvedEvent catalogSolvedEvent) {
        rabbitTemplate.convertAndSend(catalogExchange, catalogSolvedEvent);
    }*/
}
