package com.isep.acme.Producer;

import com.isep.acme.Dto.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewDeletedProducer {

    @Value("rabbitmq.review.deleted.exchange.name")
    private String reviewDeletedExchange;
    @Value("${rabbitmq.review.deleted.routing.key}")
    private String reviewDeletedRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewDeletedProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendReviewDeleted(Long reviewID) {

        LOGGER.error(String.format("Data  -> %s", reviewID));

        try{
            rabbitTemplate.convertAndSend(reviewDeletedExchange, reviewDeletedRoutingKey, reviewID);
            LOGGER.info(String.format("Review deleted %s and sent", reviewID));
        }catch(Exception e){
            LOGGER.error(String.format("Error | Review not sent -> %s", e.getMessage()));
        }
    }
}
