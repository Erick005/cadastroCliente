package com.agibank.teste.api.request;

import com.agibank.teste.annotation.MaiorDeIdade;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Schema(description = "Dados necessários para a criação de um cliente no sistema.")
public class CriaClienteRequest {

    @NotBlank(message = "O campo não pode ser vazio ou conter apenas espaços em branco.")
    @Size(min = 3, message = "Deve ter no mínimo 3 caracteres.")
    @Schema(description = "Nome completo do cliente", example = "João Silva")
    private String nome;

    @Pattern(regexp = ("[0-9]{11}"), message = "Por favor, insira o CPF sem pontos ou traços.")
    @CPF
    @Schema(description = "Número de CPF do cliente", example = "31724352024")
    private String cpf;

    @Email
    @Schema(description = "Email do cliente", example = "joao.silva@email.com")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd") // não precisa
    @MaiorDeIdade
    @Schema(description = "Data de nascimento do cliente. O cliente deve ser maior de idade.", example = "1990-05-25")
    private LocalDate dataNascimento;

    @Pattern(
            regexp = "^(?:\\+55)?(?:[1-9][0-9])?(?:9[6-9][0-9]{7}|[2-9][0-9]{7})$",
            message = "Número de telefone inválido. Deve conter apenas dígitos, com ou sem o código do país (+55) e o DDD."
    )
    @Schema(description = "Número de telefone do cliente com ou sem DDD", example = "11987654321")
    private String telefone;

    @NotBlank
    @Schema(description = "Endereço completo do cliente", example = "Rua X, 123, Centro, São Paulo, SP")
    private String endereco;

    @DecimalMin(value = "0", inclusive = false, message = "O valor não pode ser negativo.")
    @NotNull
    @Schema(description = "Saldo inicial do cliente", example = "1000.00")
    private BigDecimal saldo;
}
