package com.agibank.teste.dto;

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
@Schema(description = "DTO utilizado para enviar as informações de um cliente como resposta ao controlador.")
public class ClienteResponseDTO {

    @Schema(description = "Nome completo do cliente", example = "João Silva")
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
