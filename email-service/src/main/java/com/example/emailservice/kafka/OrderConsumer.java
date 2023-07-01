package com.example.emailservice.kafka;

import com.example.basedomains.dto.OrdersEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.emailservice.service.EmailService;
@Service
public class OrderConsumer {
    @Autowired
    private EmailService emailService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrdersEvent event){
        LOGGER.info(String.format("Order event received in stock service=> %s", event.toString()));

        emailService.sendSimpleEmail(event.getOrder().getEmail(),
                "Order Placed",
                event.getOrder().toString());
    }


}
