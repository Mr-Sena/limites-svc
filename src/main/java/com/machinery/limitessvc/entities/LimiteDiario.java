package com.machinery.limitessvc.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@EqualsAndHashCode(of = "id")
@Data // Esse recurso pode apresentar os detalhes de código com o atalho intelliJ do Plugin.
// (CTRL + SHIFT + A), Pesquisar por 'Delombok'
public class LimiteDiario {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agencia;

    private Long conta;

    private BigDecimal valor;

    private LocalDateTime data;


}
