package com.machinery.limitessvc.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.machinery.limitessvc.dto.TransactionDto;
import com.machinery.limitessvc.service.LimiteDiarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class LimiteConsumer {

    private LimiteDiarioService service;
    private ObjectMapper objectMapper;

    public LimiteConsumer(LimiteDiarioService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${app.topic}")
    public void emConsumo(final String message) {

        try {
            var transactionDto = getTransaction(message);
            service.validarLimiteDiario(transactionDto);

        } catch (IOException failure) {
            log.error(failure.getMessage());
        }
    }

    private TransactionDto getTransaction(String message) throws IOException {

        var transactionDto = objectMapper.readValue(message, TransactionDto.class);
        transactionDto.setData(LocalDateTime.now());
        return transactionDto;
    }
}
