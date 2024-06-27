package com.machinery.limitessvc.repositories;

import com.machinery.limitessvc.entities.LimiteDiario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimiteDiarioRepository extends CrudRepository<LimiteDiario, Long> {

    // Consulta automática para o banco de dados. (FindByAgenciaAndConta)
    Optional<LimiteDiario> findByAgenciaAndConta(final Long agencia, final Long conta);

}
