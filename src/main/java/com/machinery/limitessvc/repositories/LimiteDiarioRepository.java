package com.machinery.limitessvc.repositories;

import com.machinery.limitessvc.entities.LimiteDiario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimiteDiarioRepository extends CrudRepository<LimiteDiario, Long> {

    // Cosnultar automatica para o banco de dados. (FindByAgenciaAndConta)
    LimiteDiario findByAgenciaAndConta(Long agencia, Long conta);

}
