package com.machinery.limitessvc.service;

import com.machinery.limitessvc.entities.LimiteDiario;
import com.machinery.limitessvc.repositories.LimiteDiarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LimiteDiarioService {

    private LimiteDiarioRepository repository;


    public LimiteDiarioService(LimiteDiarioRepository repository) {
        this.repository = repository;
    }

    public Optional<LimiteDiario> findById(Long id) {

        return repository.findById(id);
    }
}
