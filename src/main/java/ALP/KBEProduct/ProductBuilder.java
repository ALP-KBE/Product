package ALP.KBEProduct;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ALP.RabbitMessage;
import ALP.KBEProduct.Model.Component;
import ALP.KBEProduct.Model.Product;
import ALP.KBEProduct.RabbitMQ.RabbitMQSender;

@Service
public class ProductBuilder {

    @Autowired
    RabbitMQSender productSender;
    
    private HashMap<String, Product> productMap = new HashMap<>();

    public void handle(String productName, Component component){
        Product product = productMap.get(productName);
        if(product == null){
            product = new Product(productName);
        }
        product.addComponent(component);
        System.out.println(product.toString());
        productMap.put(product.getName(), product);
        productSender.send(new RabbitMessage("product", product));
    }
}
