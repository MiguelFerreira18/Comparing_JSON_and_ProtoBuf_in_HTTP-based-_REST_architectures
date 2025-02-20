package com.isep.acme;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /* Queues */
    @Value("${rabbitmq.upvote.queue.name}")
    private String upvotedQueue;

    @Value("${rabbitmq.downvote.queue.name}")
    private String downvotedQueue;

    @Bean
    public Queue upvotedQueue() {
        return new Queue(upvotedQueue);
    }

    @Bean Queue downvotedQueue() {
        return new Queue(downvotedQueue);
    }

    /* Exchanges */
    @Value("${rabbitmq.upvote.exchange.name}")
    private String upvotesExchange;

    @Value("${rabbitmq.downvote.exchange.name}")
    private String downvotesExchange;

    @Bean
    public TopicExchange upvotesExchange() {
        return new TopicExchange(upvotesExchange);
    }

    @Bean
    public TopicExchange downvotesExchange() {
        return new TopicExchange(downvotesExchange);
    }

    /* Keys */
    @Value("${rabbitmq.upvote.routing.key}")
    private String upvoteRoutingKey;

    @Value("${rabbitmq.downvote.routing.key}")
    private String downvoteRoutingKey;

    @Bean
    public Binding upvoteBinding() {
        return BindingBuilder
        .bind(upvotedQueue())
        .to(upvotesExchange())
        .with(upvoteRoutingKey);
    }

    @Bean
    public Binding downvoteBinding() {
        return BindingBuilder
        .bind(downvotedQueue())
        .to(downvotesExchange())
        .with(downvoteRoutingKey);
    }

    @Value("${rabbitmq.review.created.queue.name}")
    private String reviewCreatedQueue;
    @Value("${rabbitmq.review.created.routing.key}")
    private String reviewCreatedRoutingKey;
    @Value("${rabbitmq.review.created.exchange.name}")
    private String reviewCreatedExchange;

    @Bean
    public Queue reviewCreatedQueue() {
        return new Queue(reviewCreatedQueue);
    }
    @Bean
    public TopicExchange reviewCreatedExchange() {
        return new TopicExchange(reviewCreatedRoutingKey);
    }
    @Bean
    public Binding reviewCreatedBinding() {
        return BindingBuilder
                .bind(reviewCreatedQueue())
                .to(reviewCreatedExchange())
                .with(reviewCreatedExchange);
    }

    @Value("${rabbitmq.review.vote.created.queue.name}")
    private String reviewVotesQueue;
    @Value("${rabbitmq.review.vote.created.exchange.name}")
    private String reviewVotesExchange;
    @Value("${rabbitmq.review.vote.created.routing.key}")
    private String reviewVotesRoutingKey;

    @Bean
    public Queue reviewVotesQueue() {
        return new Queue(reviewVotesQueue);
    }
    @Bean
    public TopicExchange reviewVotesExchange() {
        return new TopicExchange(reviewVotesExchange);
    }
    @Bean
    public Binding reviewVotesBinding() {
        return BindingBuilder
                .bind(reviewVotesQueue())
                .to(reviewVotesExchange())
                .with(reviewVotesRoutingKey);
    }

    @Value("${rabbitmq.review.vote.canceled.queue.name}")
    private String reviewVotesCanceledQueue;
    @Value("${rabbitmq.review.vote.canceled.exchange.name}")
    private String reviewVotesCanceledExchange;
    @Value("${rabbitmq.review.vote.canceled.routing.key}")
    private String reviewVotesCanceledRoutingKey;

    @Bean
    public Queue reviewVotesCanceledQueue() {
        return new Queue(reviewVotesCanceledQueue);
    }
    @Bean
    public TopicExchange reviewVotesCanceledExchange() {
        return new TopicExchange(reviewVotesCanceledExchange);
    }
    @Bean
    public Binding reviewVotesCanceledBinding() {
        return BindingBuilder
                .bind(reviewVotesCanceledQueue())
                .to(reviewVotesCanceledExchange())
                .with(reviewVotesCanceledRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    } 
}
