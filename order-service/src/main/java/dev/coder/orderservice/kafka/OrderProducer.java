package dev.coder.orderservice.kafka;

import dev.coder.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private static final String TOPIC = "final-topic";

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event => %s", orderEvent.toString()));
        //create message
        Message<OrderEvent> message = MessageBuilder
            .withPayload(orderEvent)
            .setHeader(KafkaHeaders.TOPIC, TOPIC)
            .build();
        //to publish at broker in topic "final-topic"
        this.kafkaTemplate.send(message);
    }
}
