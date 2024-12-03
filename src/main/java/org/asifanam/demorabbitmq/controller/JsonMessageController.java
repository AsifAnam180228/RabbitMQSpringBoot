package org.asifanam.demorabbitmq.controller;

import org.asifanam.demorabbitmq.dto.User;
import org.asifanam.demorabbitmq.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {

    private RabbitMQJsonProducer rabbitMQJsonProducer;


    public JsonMessageController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }
    @PostMapping("/publish")
    public ResponseEntity <String> sendJsonMessage(@RequestBody User user){
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent to the RabbitMQ Successfully");
    }
}
