package ALP.KBEProduct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReaderBuilder;

import ALP.RabbitMessage;
import ALP.KBEProduct.Model.Component;
import ALP.KBEProduct.RabbitMQ.RabbitMQSender;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import static java.lang.Thread.sleep;

@org.springframework.stereotype.Component
public class CSVParser {
    static Map<String, Component> componentsMap = null;
    @Autowired
    ProductBuilder productBuilder;
    @Autowired
    RabbitMQSender sender;
    final ObjectMapper objectMapper = new ObjectMapper();
    @Value("classpath:products.csv")
    private Resource productsPath;

    /**
     * Method to read stored csv and create a list of objects
     */
    public void parse() {
        System.out.println("startet");
        InputStreamReader productsStreamReader = null;
        try {
            productsStreamReader = new InputStreamReader(productsPath.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file read");
        if (componentsMap == null) {
            sender.send(new RabbitMessage("getComponentsCsv", ""));
            while (componentsMap == null) {
                try {
                    System.out.println("waiting");
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            System.out.println("try building");
            List<String[]> csvProducts = new CSVReaderBuilder(productsStreamReader)
                    .withSkipLines(1).build().readAll();
            for (String[] csvProduct : csvProducts) {
                for (int i = 1; i < csvProduct.length; i++) {
                    productBuilder.handle(csvProduct[0], componentsMap.get(csvProduct[i]));
                }
                System.out.println("built");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public static void save(HashMap<String, Component> map){
        componentsMap = map;
    }
}
