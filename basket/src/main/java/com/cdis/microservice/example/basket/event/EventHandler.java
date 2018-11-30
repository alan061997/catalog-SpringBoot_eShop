package com.cdis.microservice.example.basket.event;

import com.cdis.microservice.example.basket.model.BasketItem;
import com.cdis.microservice.example.basket.model.BasketUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * This class receives the events and triggers the associated
 * business logic.
 */
@Slf4j
@Component
class EventHandler {

    private static Logger logger = LoggerFactory.getLogger(EventHandler.class);

    private BasketUser basketUser;

    @RabbitListener(queues = "${user.queue}")
    void handleUserSolved(final BasketUser event) {
        logger.info("Recieved User = [ " + event.toString() + " ]");
        try {
            logger.info("Recieved User = [ " + event.toString() + " ]");
        } catch (final Exception e) {
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${product.queue}")
    void handleProductSolved(final BasketItem event) {
        logger.info("Recieved Product = [ " + event.toString() + " ]");
        try {
            logger.info("Recieved Product = [ " + event.toString() + " ]");
        } catch (final Exception e) {
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}