package ALP.KBEProduct.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Queue;

import java.io.Serializable;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    private Queue mainQueue=new Queue("philips-main-queue");

    public void send(Serializable serializable) {
        System.out.println("message wird an main gesendet");
        rabbitTemplate.convertAndSend(mainQueue.getName(), serializable);
    }
}
