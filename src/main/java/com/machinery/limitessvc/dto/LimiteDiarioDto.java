package com.machinery.limitessvc.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode( of = "id" )
public class LimiteDiarioDto {

    private Long id;
    private Long agencia;
    private Long conta;
    private BigDecimal valor;
    private LocalDateTime data;
}
