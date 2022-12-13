package dev.coder.stockservice.kafka;

import dev.coder.stockservice.dto.OrderEvent;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    private static final String TOPIC = "final-topic";
    private static final String GROUP_ID = "stock";

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload OrderEvent orderEvent, @Headers MessageHeaders headers)
        throws IOException {
        LOGGER.info("{},=> {},=> {},=> {},=> {}", orderEvent, orderEvent.getOrder().getOrderId(),
            orderEvent.getOrder().getName(), orderEvent.getOrder().getQty(),
            orderEvent.getOrder().getPrice());
        //save the order event into the database
    }
}
