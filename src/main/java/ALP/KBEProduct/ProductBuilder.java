package ALP.KBEProduct;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ALP.KBEProduct.Model.Component;
import ALP.KBEProduct.Model.Product;

@Service
public class ProductBuilder {

    private HashMap<String, Product> productMap = new HashMap<>();

    public void handle(String productName, Component component){
        Product product = productMap.get(productName);
        if(product == null){
            product = new Product(productName);
        }
        product.addComponent(component);
        productMap.put(product.getName(), product);
    }

    public Product getProduct(String name){
        return productMap.get(name);
    }

    public List<Product> getProducts(){
        return productMap.values().stream().collect(Collectors.toList());
    }
}
