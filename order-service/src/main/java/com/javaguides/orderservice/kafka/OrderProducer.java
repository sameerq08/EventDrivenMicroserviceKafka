package com.javaguides.orderservice.kafka;

import com.javaguides.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class OrderProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private NewTopic topic;

    private KafkaTemplate<String, OrderEvent> KafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> KafkaTemplate){
        this.topic = topic;
        this.KafkaTemplate = KafkaTemplate;

    }

    public void sendMessage(OrderEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<OrderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        KafkaTemplate.send(message);
    }



}
