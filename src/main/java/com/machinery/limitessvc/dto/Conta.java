package com.machinery.limitessvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Conta implements Serializable {

    private static final long serialVersionUID = -2980968685936210499L;

    private Long codigoAgencia;

    private Long codigoConta;
}
