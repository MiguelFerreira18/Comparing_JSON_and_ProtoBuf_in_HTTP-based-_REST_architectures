package com.isep.acme.Producer;

import com.isep.acme.Dto.CreateReviewVoteDTO;
import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.Dto.ReviewVoteRespDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewCreatedProducer {

    @Value("${rabbitmq.review.created.exchange.name}")
    private String reviewCreatedExchange;
    @Value("${rabbitmq.review.created.routing.key}")
    private String reviewCreatedRoutingKey;

    @Value("${rabbitmq.review.vote.created.exchange.name}")
    private String reviewCreatedVoteExchange;
    @Value("${rabbitmq.review.vote.created.routing.key}")
    private String reviewCreatedVoteRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewCreatedProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendReviewCreated(ReviewDTO reviewDTO) {

        LOGGER.info(String.format("Data  -> %s", reviewDTO.getIdReview()));

        try{
            rabbitTemplate.convertAndSend(reviewCreatedExchange, reviewCreatedRoutingKey, reviewDTO);
            LOGGER.info(String.format("Review created and sent -> %s", reviewDTO.toString()));
        }catch(Exception e){
            LOGGER.error(String.format("Error | Review not sent -> %s", e.getMessage()));
        }
    }

    public void sendReviewCreatedWithVote(ReviewVoteRespDTO reviewDTO) {

        try{
            rabbitTemplate.convertAndSend(reviewCreatedVoteExchange, reviewCreatedVoteRoutingKey, reviewDTO);
            LOGGER.info(String.format("Review created and sent -> %s", reviewDTO.toString()));
        }catch(Exception e){
            LOGGER.error(String.format("Error | Review not sent -> %s", e.getMessage()));
        }
    }
}
