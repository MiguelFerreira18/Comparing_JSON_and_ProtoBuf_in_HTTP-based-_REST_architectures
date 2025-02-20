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
public class AmqpConfig {
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.queue.name.product.approval}")
    private String queueNameApproval;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.exchange.name.approvals}")
    private String exchangeNameApprovals;

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
    public Queue productQueue() {
        return new Queue(queueName);
    }

    @Bean
    public Queue productApprovalQueue() {
        return new Queue(queueNameApproval);
    }

    @Bean
    public TopicExchange productExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public TopicExchange productExchangeApprovals() {
        return new TopicExchange(exchangeNameApprovals);
    }

    @Bean
    public Binding productBinding() {
        return BindingBuilder.bind(productQueue()).to(productExchange()).with(routingKey);
    }
    @Bean
    public Binding productApprovalBinding() {
        return BindingBuilder.bind(productApprovalQueue()).to(productExchangeApprovals()).with(routingKey);
    }
}
