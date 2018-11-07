package com.cdis.microservice.example.auth.event;

import com.cdis.microservice.example.auth.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    // The exchange to use to send anything related to Multiplication
    private String servicioExchange;

    // The routing key to use to send this particular event
    private String servicioSolvedRoutingKey;

    public static Logger logger = LoggerFactory.getLogger(EventDispatcher.class);

    public EventDispatcher() {
    }

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${user.exchange}") final String servicioExchange,
                    @Value("${user.solved.key}") final String servicioSolvedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.servicioExchange = servicioExchange;
        this.servicioSolvedRoutingKey = servicioSolvedRoutingKey;
    }

    public void send(final UserSolvedEvent userEvent) {
        logger.info("USER SENT = [ " + userEvent + " ]");
        rabbitTemplate.convertAndSend(
                servicioExchange,
                servicioSolvedRoutingKey,
                userEvent);
    }

    public void send(User userCredentials) {
    }
}
