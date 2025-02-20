package com.isep.acme.consumer;

import com.isep.acme.dto.CreateReviewVoteDTO;
import com.isep.acme.dto.ReviewDTO;
import com.isep.acme.model.Review;
import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.producer.ReviewProducer;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.IVoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ReviewConsumer {

    @Autowired
    ReviewRepository repository;

    @Autowired
    IVoteService voteService;

    @Autowired
    ReviewProducer reviewProducer;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewConsumer.class);

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

        repository.save(review);

        LOGGER.info(String.format("Review %s saved", review.toString()));
    }

    @RabbitListener (queues = "${rabbitmq.review.vote.created.queue.name}")
    @Transactional
    public void createVoteWithReview(CreateReviewVoteDTO reviewDto) {

        LOGGER.info(String.format("Received review created -> %s", reviewDto.toString()));

        try{
            Review review = new Review(
                    reviewDto.getReviewDTO().getIdReview(),
                    reviewDto.getReviewDTO().getReviewText(),
                    LocalDate.now(),
                    reviewDto.getReviewDTO().getApprovalStatus(),
                    reviewDto.getReviewDTO().getFunFact(),
                    reviewDto.getReviewDTO().getRating()
            );

            repository.save(review);

            LOGGER.info(String.format("Review %s saved", review.toString()));
        }catch (Exception e){
            LOGGER.error("Error creating review");
            reviewProducer.sendRollbackEvent(reviewDto.getReviewDTO().getIdReview());
            return;
        }

        try{
            Long reviewId = reviewDto.getReviewDTO().getIdReview();

            VoteReviewDTO voteDTO = reviewDto.getVoteReviewDTO();

            if (!("upVote".equals(voteDTO.getVote()) || "downVote".equals(voteDTO.getVote()))){
                LOGGER.error("Erro creating the vote");
                reviewProducer.sendRollbackEvent(reviewDto.getReviewDTO().getIdReview());
                return;
            }

            VoteReviewDTO added = "upVote".equals(voteDTO.getVote()) ?
                    voteService.upvoteReview(reviewId, voteDTO.getUserID()) :
                    voteService.downvoteReview(reviewId,voteDTO.getUserID());

            if(added == null){
                LOGGER.error("Erro ao adicionar voto");
                reviewProducer.sendRollbackEvent(reviewDto.getReviewDTO().getIdReview());
                return;
            }
        }
        catch (Exception e){
            LOGGER.error("Error processing vote " + e.getMessage());
            reviewProducer.sendRollbackEvent(reviewDto.getReviewDTO().getIdReview());
            return;
        }

        LOGGER.info("Review com voto criado com sucesso");
    }
}
