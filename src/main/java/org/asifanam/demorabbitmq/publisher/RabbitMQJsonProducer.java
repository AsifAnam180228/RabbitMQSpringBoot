package org.asifanam.demorabbitmq.publisher;


import org.asifanam.demorabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.jsonRouting.key}")
    private String jsonRoutingKey;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    public void sendJsonMessage(User user){
        LOGGER.info("Sending message -> {} exchange name ->{} jsonRoutingKey->{}", user.toString(), exchangeName, jsonRoutingKey);
        rabbitTemplate.convertAndSend(exchangeName, jsonRoutingKey, user);
    }

}
