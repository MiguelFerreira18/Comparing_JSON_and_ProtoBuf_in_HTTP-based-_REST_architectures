package com.isep.acme.message;

import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.ProductMessage;
import com.isep.acme.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Value("${rabbitmq.message.product.insert}")
    private String messageInsert;
    @Value("${rabbitmq.message.product.update}")
    private String messageUpdate;
    @Value("${rabbitmq.message.product.delete}")
    private String messageDelete;



    @Autowired
    private ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);


    /**
     * Consume message from the queue defined in application.properties that is
     * associated with the exchange (Topic)
     * @param message message received from the message broker
     */
    @RabbitListener(queues = "${rabbitmq.queue.name.product}")
    public void consumeMessageFromQueue(ProductMessage message) {

        if (message.getSenderID() != productService.getSenderID()) {
            if(message.getMessage().equalsIgnoreCase(messageInsert)){
                productService.create(new Product(message.getProduct().getSku(), message.getProduct().getDesignation(), message.getProduct().getDescription()));
            }else if(message.getMessage().equalsIgnoreCase(messageUpdate)){
                productService.updateBySku(message.getProduct().getSku(), new Product(message.getProduct().getSku(), message.getProduct().getDesignation(), message.getProduct().getDescription(), message.getProduct().isPublished()));
            }else if(message.getMessage().equalsIgnoreCase(messageDelete)){
                productService.deleteBySku(message.getProduct().getSku());
            }
        }
    }
}

