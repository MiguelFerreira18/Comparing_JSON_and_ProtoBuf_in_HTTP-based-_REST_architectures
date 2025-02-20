package com.isep.acme.message;

import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.ReviewMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReviewProducer {
    //Routing keys
    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    //Messages
    @Value("${rabbitmq.message.review.insert}")
    private String messageInsert;
    @Value("${rabbitmq.message.review.delete}")
    private String messageDelete;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void insertReviewMessage(int senderID, Review review) {
        ReviewMessage reviewMessage = new ReviewMessage(messageInsert,senderID,review);
        rabbitTemplate.convertAndSend(exchangeName,routingKey ,reviewMessage);
        System.out.println("Sent Message: " + reviewMessage.getMessage());
    }
    public void deleteReviewMessage(int senderID, Review review) {
        ReviewMessage reviewMessage = new ReviewMessage(messageDelete,senderID,review);
        rabbitTemplate.convertAndSend(exchangeName,routingKey ,reviewMessage);
        System.out.println("Sent Message: " + reviewMessage.getMessage());
    }
}

