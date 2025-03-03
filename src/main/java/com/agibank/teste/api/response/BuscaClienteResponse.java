package com.agibank.teste.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resposta para busca de um cliente no sistema.")
public class BuscaClienteResponse {

    @Schema(description = "Nome do cliente.", example = "João Silva")
    private String nome;

    @Schema(description = "CPF do cliente.", example = "31724352024")
    private String cpf;

    @Schema(description = "E-mail do cliente.", example = "joao.silva@dominio.com")
    private String email;

    @Schema(description = "Data de nascimento do cliente.", example = "1980-05-15")
    private LocalDate dataNascimento;

    @Schema(description = "Número de telefone do cliente.", example = "11998765432")
    private String telefone;

    @Schema(description = "Endereço completo do cliente.", example = "Rua das Flores, 123, São Paulo, SP")
    private String endereco;

    @Schema(description = "Saldo atual do cliente.", example = "1500.75")
    private BigDecimal saldo;
}
