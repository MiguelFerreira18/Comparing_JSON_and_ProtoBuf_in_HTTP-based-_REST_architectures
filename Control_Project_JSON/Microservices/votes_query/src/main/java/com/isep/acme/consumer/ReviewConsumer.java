package com.isep.acme.consumer;

import com.isep.acme.dto.ReviewDTO;
import com.isep.acme.model.Review;
import com.isep.acme.repositories.ReviewRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.services.IVoteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Service
public class ReviewConsumer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteConsumer.class);

    @Autowired
    private IVoteService voteService;

    @Autowired
    ReviewRepository repository;

    @RabbitListener (queues = "${rabbitmq.review.created.queue.name}")
    public void receive(ReviewDTO reviewDto) {

        LOGGER.info(String.format("Received review created -> %s", reviewDto.toString()));

        Review review = new Review(
                reviewDto.getIdReview(),
                reviewDto.getReviewText(),
                LocalDate.now(),
                reviewDto.getApprovalStatus(),
                reviewDto.getFunFact(),
                reviewDto.getRating()
        );

        if(repository.findByReviewId(review.getReviewId()) == null){
            LOGGER.info("Review not found");
            return;
        }

        repository.save(review);

        LOGGER.info(String.format("Review %s saved", review.toString()));
    }
}
