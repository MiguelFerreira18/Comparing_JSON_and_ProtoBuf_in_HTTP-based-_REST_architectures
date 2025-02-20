package com.isep.acme.message;

import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.H2Entity.ProductAcceptanceEvent;
import com.isep.acme.model.ProductApprovalMessage;
import com.isep.acme.model.ProductMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductProducer {
    //Routing keys
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.exchange.name.approvals}")
    private String exchangeNameApprovals;

    //Messages
    @Value("${rabbitmq.message.product.insert}")
    private String messageInsert;
    @Value("${rabbitmq.message.product.update}")
    private String messageUpdate;
    @Value("${rabbitmq.message.product.delete}")
    private String messageDelete;
    @Value("${rabbitmq.message.product.approval.insert}")
    private String messageApprovalInsert;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void insertProductMessage(int senderID, Product product) {
        ProductMessage productMessage = new ProductMessage(messageInsert,senderID,product);
        rabbitTemplate.convertAndSend(exchangeName,routingKey ,productMessage);
        System.out.println("Sent Message: " + productMessage.getMessage());
    }
    public void updateProductMessage(int senderID, Product product) {
        ProductMessage productMessage = new ProductMessage(messageUpdate,senderID,product);
        rabbitTemplate.convertAndSend(exchangeName,routingKey ,productMessage);
        System.out.println("UPDATE Sent Message: " + productMessage.getMessage());
    }
    public void deletePoductMessage(int senderID, Product product) {
        ProductMessage productMessage = new ProductMessage(messageDelete,senderID,product);
        rabbitTemplate.convertAndSend(exchangeName,routingKey ,productMessage);
        System.out.println("Sent Message: " + productMessage.getMessage());
    }
    public void insertApprovalMessage(int senderID, ProductAcceptanceEvent product) {
        ProductApprovalMessage productMessage = new ProductApprovalMessage(messageApprovalInsert,senderID,product);
        System.out.println("Sent Message: " + productMessage.getMessage());
        rabbitTemplate.convertAndSend(exchangeNameApprovals,routingKey ,productMessage);
        System.out.println("Sent Message: " + productMessage.getMessage());
    }
}
