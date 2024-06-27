package com.machinery.limitessvc.service;

import com.machinery.limitessvc.entities.LimiteDiario;
import com.machinery.limitessvc.repositories.LimiteDiarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class LimiteDiarioService {

    private LimiteDiarioRepository repository;

    @Value("${limite.valor:0}")
    private BigDecimal valorDiario;


    public LimiteDiarioService(LimiteDiarioRepository repository) {
        this.repository = repository;
    }


    public Optional<LimiteDiario> buscarLimiteDiario( final Long agencia, final Long conta ) {

        var data = repository.findByAgenciaAndConta(agencia, conta);

        if (data.isEmpty()) {
            var limiteDiario = new LimiteDiario();

            limiteDiario.setValor(valorDiario);
            limiteDiario.setConta(conta);
            limiteDiario.setAgencia(agencia);
            limiteDiario.setData(LocalDate.now());
            return Optional.of(repository.save(limiteDiario));
        }

        return data;
    }

    public Optional<LimiteDiario> findById(Long id) {

        return repository.findById(id);
    }
}
