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

    private Queue mainQueue=new Queue("main-queue");

    public void send(Serializable serializable) {
        rabbitTemplate.convertAndSend(mainQueue.getName(), serializable);
    }
}
