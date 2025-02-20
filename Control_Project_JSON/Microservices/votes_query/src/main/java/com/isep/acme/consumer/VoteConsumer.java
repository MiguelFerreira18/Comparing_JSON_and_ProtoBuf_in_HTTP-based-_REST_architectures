package com.isep.acme.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.repositories.VoteRepository;
import com.isep.acme.services.IReviewService;
import com.isep.acme.services.IVoteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class VoteConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteConsumer.class);

    @Autowired
    private IVoteService voteService;

    // Subscrever o add review e o delete review

    @RabbitListener(queues = "${rabbitmq.upvote.queue.name}")
    public void upvotedQueue(VoteReviewDTO voteDto) {
        LOGGER.info(String.format("Received JSON message -> %s", voteDto.toString()));

        try{
            voteService.createUpVote(voteDto);
            LOGGER.info(String.format("Voted up added", voteDto.toString()));
        }
        catch (UnsupportedOperationException e){
            LOGGER.info(String.format("Error creating review -> %s", e.getMessage()));
        }
    }

    @RabbitListener(queues = "${rabbitmq.downvote.queue.name}")
    public void downvotedQueue(VoteReviewDTO voteDto) {
        LOGGER.info(String.format("Received JSON message -> %s", voteDto.toString()));

        try{
            voteService.createDownVote(voteDto);
            LOGGER.info(String.format("Voted down added", voteDto.toString()));
        }
        catch (Exception e){
            LOGGER.info(String.format("Error creating review -> %s", e.getMessage()));
        }
    }
}