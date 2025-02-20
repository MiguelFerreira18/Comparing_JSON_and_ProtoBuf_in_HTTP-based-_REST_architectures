package com.isep.acme.producer;

import com.isep.acme.dto.VoteReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewProducer {

    @Value("${rabbitmq.review.vote.canceled.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.review.vote.canceled.routing.key}")
    private String routing;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendRollbackEvent(Long reviewID) {
        try{
            rabbitTemplate.convertAndSend(exchange, routing, reviewID);
            LOGGER.info(String.format("Rollback message sent -> %s", reviewID.toString()));
        }catch(Exception e){
            LOGGER.error(String.format("Rollback message not sent -> %s", e.getMessage()));
        }
    }
}
