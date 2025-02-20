package com.isep.acme.message;

import com.isep.acme.Dto.CreateReviewDTO;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.ReviewMessage;
import com.isep.acme.services.ProductService;
import com.isep.acme.services.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReviewConsumer {

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Autowired
    private ReviewService reviewService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessageFromQueue(ReviewMessage message) {

        LOGGER.info("Message received from queue : " + message);
        LOGGER.info("Message received from queue : " + message.getMessage());
        LOGGER.info("Message received from queue : " + message.getSenderID());
        if (message.getSenderID() != reviewService.getSenderID()) {
            switch (message.getMessage()){
                case "insert-review":
                    CreateReviewDTO createReviewDTO = new CreateReviewDTO();
                    createReviewDTO.setReviewText(message.getReview().getReviewText());
                    createReviewDTO.setUserID(message.getReview().getUser().getUserId());
                    createReviewDTO.setRating(message.getReview().getRating().getRate());
                    reviewService.create(createReviewDTO, message.getReview().getProduct().getSku());
                    break;
                case "delete-review":
                    reviewService.DeleteReview(message.getReview().getIdReview());
                    break;
            }

        }


    }
}
