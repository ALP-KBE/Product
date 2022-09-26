package ALP.KBEProduct.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Queue;

import java.io.Serializable;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private Queue mainQueue;

    public void send(Serializable serializable) {
        System.out.println("send to gateway");
        rabbitTemplate.convertAndSend(mainQueue.getName(), serializable);
    }
}
