package com.machinery.limitessvc.service;

import com.machinery.limitessvc.dto.TransactionDto;
import com.machinery.limitessvc.entities.LimiteDiario;
import com.machinery.limitessvc.events.LimiteProducer;
import com.machinery.limitessvc.repositories.LimiteDiarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class LimiteDiarioService {

    private LimiteDiarioRepository limiteDiarioRepository;
    private LimiteProducer kafkaProducer;

    @Value("${limite.valor:0}")
    private BigDecimal valorDiario;


    public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository, LimiteProducer kafkaProducer) {
        this.limiteDiarioRepository = limiteDiarioRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public Optional<LimiteDiario> buscarLimiteDiario(final Long agencia, final Long conta ) {

        var data = limiteDiarioRepository.findByAgenciaAndConta(agencia, conta);

        if (data.isEmpty()) {
            var limiteDiario = new LimiteDiario();

            limiteDiario.setValor(valorDiario);
            limiteDiario.setConta(conta);
            limiteDiario.setAgencia(agencia);
            limiteDiario.setData(LocalDateTime.now());
            return Optional.of(limiteDiarioRepository.save(limiteDiario));
        }

        return data;
    }

    public Optional<LimiteDiario> findById(Long id) {

        return limiteDiarioRepository.findById(id);
    }


    public void validarLimiteDiario(TransactionDto transactionDto) {

        var limiteDiario = limiteDiarioRepository.findAccountLastDate(transactionDto.getConta().getCodigoAgencia(),
                transactionDto.getConta().getCodigoConta(), LocalDate.now());

        if (Objects.isNull(limiteDiario)) {

            limiteDiario = new LimiteDiario();
            limiteDiario.setAgencia(transactionDto.getConta().getCodigoAgencia());
            limiteDiario.setConta(transactionDto.getConta().getCodigoConta());
            limiteDiario.setValor(valorDiario);
            limiteDiario.setData( transactionDto.getData() );

            limiteDiario = limiteDiarioRepository.save(limiteDiario);
        }

        if (limiteDiario.getValor().compareTo(transactionDto.getValue()) < 0) {

            log.info("Valor de transação excede o limite diário: {}", transactionDto);
            transactionDto.suspeitaFraude();
        }

        else if (limiteDiario.getValor().compareTo(BigDecimal.valueOf(20000L)) > 0) {

            transactionDto.emAnaliseHumana();
            log.info("Transação em atendimento de análise: {}", transactionDto);
        }

        else {

            transactionDto.analisada();
            log.info("Transação analisada com sucesso. \n{}", transactionDto);
            limiteDiario.setValor(limiteDiario.getValor().subtract(transactionDto.getValue()));
            limiteDiarioRepository.save(limiteDiario);
        }

        kafkaProducer.enviarMsg(transactionDto);
    }
}
