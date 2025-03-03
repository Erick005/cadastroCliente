package com.agibank.teste.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {

    private String nome;

    private String cpf;

    private String email;

    private LocalDate dataNascimento;

    private String telefone;

    private String endereco;

    private BigDecimal saldo;
}
