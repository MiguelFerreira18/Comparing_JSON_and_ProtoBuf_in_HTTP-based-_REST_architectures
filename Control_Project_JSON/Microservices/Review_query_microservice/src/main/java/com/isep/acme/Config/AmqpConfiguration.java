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
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
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
}
