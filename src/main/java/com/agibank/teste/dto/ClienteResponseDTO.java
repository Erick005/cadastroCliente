package com.agibank.teste.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteResponseDTO {

    private String nome;

    private String cpf;

    private String email;

    private LocalDate dataNascimento;

    private String telefone;

    private String endereco;

    private BigDecimal saldo;
}
