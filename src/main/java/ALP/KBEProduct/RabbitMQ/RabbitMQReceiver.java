package ALP.KBEProduct.RabbitMQ;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ALP.RabbitMessage;
import ALP.KBEProduct.ProductBuilder;

@Component
@RabbitListener(queues = "product-queue", id = "listener")
public class RabbitMQReceiver {

    @Autowired
    ProductBuilder productBuilder;

    @RabbitHandler
    public void receiver(RabbitMessage message) {
        if (message.getType().equals("postComponent")) {
            if (message.getValue() instanceof LinkedHashMap) {
                LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) message.getValue();
                ALP.KBEProduct.Model.Component comp = processLinkedHashMap(linkedHashMap);
                productBuilder.handle((String) message.getAdditionalField(), comp);
            } else {
                productBuilder.handle((String) message.getAdditionalField(),
                        (ALP.KBEProduct.Model.Component) message.getValue());
            }
        } else if (message.getType().equals("postComponents")) {
            if (message.getValue() instanceof ArrayList) {
                String productName = (String) message.getAdditionalField();
                ArrayList<LinkedHashMap<String, String>> arrayList = (ArrayList<LinkedHashMap<String, String>>) message
                        .getValue();
                for (LinkedHashMap<String, String> linkedHashMap : arrayList) {
                    ALP.KBEProduct.Model.Component comp = processLinkedHashMap(linkedHashMap);
                    productBuilder.handle((String) message.getAdditionalField(), comp);
                }
            } else {
                productBuilder.handle((String) message.getAdditionalField(),
                        (ALP.KBEProduct.Model.Component) message.getValue());
            }
        }
    }

    private ALP.KBEProduct.Model.Component processLinkedHashMap(LinkedHashMap<String, String> linkedHashMap) {
        ALP.KBEProduct.Model.Component component = new ALP.KBEProduct.Model.Component();
        linkedHashMap.forEach((key, val) -> component.set(key, val));
        return component;
    }
}
