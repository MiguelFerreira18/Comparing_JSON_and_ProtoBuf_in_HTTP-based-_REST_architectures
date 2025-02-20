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

    @Value("${rabbitmq.upvote.queue.name}")
    private String upvotedQueue;

    @Value("${rabbitmq.upvote.exchange.name}")
    private String upvotesExchange;

    @Value("${rabbitmq.upvote.routing.key}")
    private String upvoteRoutingKey;

    @Bean
    public Queue upvotedQueue() {
        return new Queue(upvotedQueue);
    }

    @Bean
    public TopicExchange upvotesExchange() {
        return new TopicExchange(upvotesExchange);
    }

    @Bean
    public Binding upvoteBinding() {
        return BindingBuilder
        .bind(upvotedQueue())
        .to(upvotesExchange())
        .with(upvoteRoutingKey);
    }

    @Value("${rabbitmq.downvote.queue.name}")
    private String downvotedQueue;

    @Value("${rabbitmq.downvote.exchange.name}")
    private String downvotesExchange;

    @Value("${rabbitmq.downvote.routing.key}")
    private String downvoteRoutingKey;

    @Bean Queue downvotedQueue() {
        return new Queue(downvotedQueue);
    }

    @Bean
    public TopicExchange downvotesExchange() {
        return new TopicExchange(downvotesExchange);
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
