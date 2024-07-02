package com.machinery.limitessvc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@ToString(of = {"uuid", "situacao"}) // Evitar expor os dados mais sensíveis do cliente. (Somente id e situacao.)
public class TransactionDto {

    @Id
    private UUID uuid;

    private BigDecimal value;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;

    private Conta conta;
    private BeneficiarioDto beneficiario;
    private TipoTransacao tipoTransacao;
    private SituacaoEnum situacao;


    public void suspeitaFraude() {
        situacao = SituacaoEnum.EM_SUSPEITA_FRAUDE;
    }

    public void analisada() {
        situacao = SituacaoEnum.ANALISADA;
    }

    public void emAnaliseHumana() {
        situacao = SituacaoEnum.EM_ANALISE_HUMANA;
    }

}
