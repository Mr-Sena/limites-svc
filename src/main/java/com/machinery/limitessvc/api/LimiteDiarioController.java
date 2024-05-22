package com.machinery.limitessvc.api;

import com.machinery.limitessvc.entities.LimiteDiario;
import com.machinery.limitessvc.service.LimiteDiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class LimiteDiarioController {

    LimiteDiarioService service;

    public LimiteDiarioController(LimiteDiarioService service) {
        this.service = service;
    }

    @GetMapping(value = "/limite-diario/{id}")
    public LimiteDiario findById(@PathVariable("id") Long id) {

        Optional<LimiteDiario> queryResult = service.findById(id);

        if(queryResult.isPresent()) {
            var response = queryResult.get();
            return response;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível localizar o recurso.");
    }
}
