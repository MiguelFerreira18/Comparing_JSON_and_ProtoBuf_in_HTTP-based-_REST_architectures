package com.isep.acme.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class consumer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(consumer.class);

    
    @RabbitListener(queues = "${rabbitmq.review.fetch.queue.name}")
    public void receive() {
        LOGGER.info(String.format("Received fetch request"));
    }
}
