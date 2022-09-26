package ALP.KBEProduct.RabbitMQ;

import java.util.ArrayList;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ALP.RabbitMessage;
import ALP.KBEProduct.ProductBuilder;
import ALP.KBEProduct.Model.Component;
import ALP.KBEProduct.Model.Product;

@org.springframework.stereotype.Component
@RabbitListener(queues = "product-queue", id = "listener")
public class RabbitMQReceiver {

    @Autowired
    ProductBuilder productBuilder;
    @Autowired
    RabbitMQSender sender;
    final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitHandler
    public void receiver(RabbitMessage message) throws JsonMappingException, JsonProcessingException {
        if (message.getType().equals("postComponent") || message.getType().equals("postComponents")) {
            if (message.getType().equals("postComponents")) {
                ArrayList<Component> components = objectMapper.readValue((String) message.getValue(),
                        new TypeReference<ArrayList<Component>>() {
                        });
                components.forEach(comp -> productBuilder.handle((String) message.getAdditionalField(), comp));
            } else {
                Component component = objectMapper.readValue((String) message.getValue(),
                        new TypeReference<Component>() {
                        });
                productBuilder.handle((String) message.getAdditionalField(), component);
            }
            Product prod = productBuilder.getProduct((String) message.getAdditionalField());
            RabbitMessage rabbitMessage = new RabbitMessage("product", objectMapper.writeValueAsString(prod));
            sender.send(rabbitMessage);
        } else if (message.getType().equals("getProducts")) {
            RabbitMessage rabbitMessage = new RabbitMessage("product",
                    objectMapper.writeValueAsString(productBuilder.getProducts()));
            sender.send(rabbitMessage);
        }
    }
}
