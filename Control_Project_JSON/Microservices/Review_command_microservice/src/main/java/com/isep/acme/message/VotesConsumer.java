package com.isep.acme.message;

import com.isep.acme.Dto.CreateReviewVoteDTO;
import com.isep.acme.services.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotesConsumer {

    @Autowired
    private ReviewService reviewService;

    private static final Logger LOGGER = LoggerFactory.getLogger(VotesConsumer.class);

    @RabbitListener(queues = "${rabbitmq.review.vote.canceled.queue.name}")
    public void cancelReview(Long reviewID) {

        LOGGER.info("Review was canceled");

        reviewService.DeleteReview(reviewID);

        LOGGER.info(String.format("Review %s deleted", reviewID.toString()));
    }
}
