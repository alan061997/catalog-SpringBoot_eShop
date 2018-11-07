package com.cdis.microservice.example.catalog.event;

import com.cdis.microservice.example.catalog.model.client.User;
import com.cdis.microservice.example.catalog.repository.client.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class receives the events and triggers the associated
 * business logic.
 */
@Slf4j
@Component
class EventHandler {

    public static Logger logger = LoggerFactory.getLogger(EventHandler.class);

    private UserRepository userRepository;

    @Autowired
    EventHandler(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = "${user.queue}")
    void handleMultiplicationSolved(final User event) {
        logger.info("Recieved User = [ " + event.toString() + " ]");
        try {
            userRepository.save(event);
        } catch (final Exception e) {
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}