package com.wojciech.publisher;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {


    private RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @GetMapping("/message")
    public String receiveMessage(){
        Object message = rabbitTemplate.receiveAndConvert("Course");
        return("odebrano wiadomosc z rabbtmq"+message.toString());

    }
    @RabbitListener(queues = "Course")
    public void listenerMessage(String message) {
        System.out.println(message);
    }
}
