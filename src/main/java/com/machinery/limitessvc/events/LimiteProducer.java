package com.machinery.limitessvc.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machinery.limitessvc.dto.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LimiteProducer {

    @Value("${app.returnTopic}")
    private String returnTopic;

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public LimiteProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    public void enviarMsg(final TransactionDto transactionDto) {

        try {

            String payload = objectMapper.writeValueAsString(transactionDto);

            Message<String> message = MessageBuilder.withPayload(payload)
                    .setHeader(KafkaHeaders.TOPIC, returnTopic)
                    .setHeader(KafkaHeaders.PARTITION, 0)
                    .build();

            kafkaTemplate.send(message);
        }catch (Exception problema) {
            log.error(problema.getMessage());
        }
    }
}
