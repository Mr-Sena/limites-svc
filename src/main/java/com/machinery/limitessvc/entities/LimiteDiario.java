package com.machinery.limitessvc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table
@EqualsAndHashCode(of = "id")
@Data // Esse recurso pode apresentar os detalhes de código com o atalho intelliJ do Plugin.
// (CTRL + SHIFT + A), Pesquisar por 'Delombok'
public class LimiteDiario {

    @Id
    private Long id;

    private Long agencia;

    private Long conta;

    private BigDecimal valor;



    public String toString() {
        return "LimiteDiario(id=" + this.getId() + ", agencia=" + this.getAgencia() +
                ", conta=" + this.getConta() + ", valor=" + this.getValor() + ")";
    }
}
