package com.isep.acme.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
    @Value("${rabbitmq.routing.key}")
    private String routingKeyReview;
    @Value("${rabbitmq.queue.name}")
    private String queueNameReview;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeNameReview;

    @Value("${rabbitmq.routing.key.product}")
    private String routingKeyProduct;
    @Value("${rabbitmq.queue.name.product}")
    private String queueNameProduct;
    @Value("${rabbitmq.exchange.name.product}")
    private String exchangeNameProduct;


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }



    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue reviewQueue() {
        return new Queue(queueNameReview);
    }

    @Bean
    public TopicExchange reviewExchange() {
        return new TopicExchange(exchangeNameReview);
    }

    @Bean
    public Binding reviewBinding() {
        return BindingBuilder.bind(reviewQueue()).to(reviewExchange()).with(routingKeyReview);
    }

    @Bean
    public Queue productQueue() {
        return new Queue(queueNameProduct);
    }

    @Bean
    public TopicExchange productExchange() {
        return new TopicExchange(exchangeNameProduct);
    }

    @Bean
    public Binding productBinding() {
        return BindingBuilder.bind(productQueue()).to(productExchange()).with(routingKeyProduct);
    }

    @Value("${rabbitmq.review.created.queue.name}")
    private String reviewCreatedQueue;
    @Value("${rabbitmq.review.created.exchange.name}")
    private String reviewCreatedExchange;
    @Value("${rabbitmq.review.created.routing.key}")
    private String reviewCreatedRoutingKey;

    @Bean
    public Queue reviewCreatedQueue() {
        return new Queue(reviewCreatedQueue);
    }
    @Bean
    public TopicExchange reviewCreatedExchange() {
        return new TopicExchange(reviewCreatedExchange);
    }
    @Bean
    public Binding reviewCreatedBinding() {
        return BindingBuilder
                .bind(reviewCreatedQueue())
                .to(reviewCreatedExchange())
                .with(reviewCreatedRoutingKey);
    }

    @Value("${rabbitmq.review.deleted.queue.name}")
    private String reviewDeletedQueue;
    @Value("${rabbitmq.review.deleted.exchange.name}")
    private String reviewDeletedExchange;
    @Value("${rabbitmq.review.deleted.routing.key}")
    private String reviewDeletedRoutingKey;

    @Bean
    public Queue reviewDeletedQueue() {
        return new Queue(reviewDeletedQueue);
    }
    @Bean
    public TopicExchange reviewDeletedExchange() {
        return new TopicExchange(reviewDeletedExchange);
    }
    @Bean
    public Binding reviewDeletedBinding() {
        return BindingBuilder
                .bind(reviewDeletedQueue())
                .to(reviewDeletedExchange())
                .with(reviewDeletedRoutingKey);
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
}
