package org.asifanam.demorabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.jsonQueue.name}")
    private String jsonQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.jsonRouting.key}")
    private String jsonRoutingKey;

    Logger LOGGER = LoggerFactory.getLogger(RabbitMQConfig.class);

    // Creating queue bean
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    //creating json Queue
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueueName);
    }

    // Creating exchange bean

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    // Binding queue to exchange with routing key
    @Bean
    public Binding binding() {
        LOGGER.info("Binding Queue -> {} to Exchange -> {} with routing key -> {}", queueName, exchangeName, routingKey);
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    //binding json queue to exchange with json routing key
    @Bean
    public Binding jsonBinding(){
        LOGGER.info("Binding JSONQueue -> {} to Exchange -> {} with JSONRouting key -> {}", jsonQueueName, exchangeName, jsonRoutingKey);
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(jsonRoutingKey);
    }

//    @Bean
//    public MessageConverter messageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(messageConverter());
//        return rabbitTemplate;
//    }



    //connection factory

    //RabbitTemplate

    //RabbitAdmin

    //autoconfiguration will do these
}
