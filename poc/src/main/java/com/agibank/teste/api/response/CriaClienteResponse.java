package com.agibank.teste.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Resposta retornada após a criação de um novo cliente.")
public class CriaClienteResponse {

    @Schema(description = "Nome do cliente", example = "João Silva")
    private String nome;

    @Schema(description = "Número de CPF do cliente", example = "31724352024")
    private String cpf;

    @Schema(description = "Email do cliente", example = "joao.silva@email.com")
    private String email;

    @Schema(description = "Data de nascimento do cliente", example = "1990-05-25")
    private LocalDate dataNascimento;

    @Schema(description = "Número de telefone do cliente", example = "11987654321")
    private String telefone;

    @Schema(description = "Endereço do cliente", example = "Rua X, 123, Centro, São Paulo, SP")
    private String endereco;

    @Schema(description = "Saldo do cliente", example = "1000.00")
    private BigDecimal saldo;
}
