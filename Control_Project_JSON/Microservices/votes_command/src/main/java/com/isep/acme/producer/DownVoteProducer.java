package com.isep.acme.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.isep.acme.dto.VoteReviewDTO;

@Service
public class DownVoteProducer {

    @Value("${rabbitmq.downvote.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.downvote.routing.key}")
    private String routing;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UpVoteProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendDownvotedMessage(VoteReviewDTO voteDto) {
         try{
            rabbitTemplate.convertAndSend(exchange, routing, voteDto);
            LOGGER.info(String.format("Upvoted message sent -> %s", voteDto.toString()));
        }catch(Exception e){
            LOGGER.error(String.format("Upvoted message not sent -> %s", e.getMessage()));
        }
    }
}
