package com.agibank.teste.api.request;

import com.agibank.teste.annotation.MaiorDeIdade;
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
@NoArgsConstructor
@Schema(description = "Dados necessários para atualizar o cadastro de um cliente.")
public class AtualizaClienteRequest {

    @Size(min = 3, message = "Deve ter no mínimo 3 caracteres.")
    @Schema(description = "Nome do cliente. Deve ter no mínimo 3 caracteres.", example = "João Silva")
    private String nome;

    @Pattern(regexp = ("[0-9]{11}"), message = "Por favor, insira o CPF sem pontos ou traços.")
    @CPF
    @Schema(description = "CPF do cliente (somente números, sem pontos ou traços).", example = "31724352024")
    private String cpf;

    @Email
    @Schema(description = "E-mail do cliente. Deve estar no formato correto.", example = "joao.silva@dominio.com")
    private String email;

    @MaiorDeIdade
    @Schema(description = "Data de nascimento do cliente. Deve ser maior de idade.", example = "1980-05-15")
    private LocalDate dataNascimento;

    @Pattern(
            regexp = "^(?:\\+55)?(?:[1-9][0-9])?(?:9[6-9][0-9]{7}|[2-9][0-9]{7})$",
            message = "Número de telefone inválido. Deve conter apenas dígitos, com ou sem o código do país (+55) e o DDD."
    )
    @Schema(description = "Número de telefone do cliente (com ou sem o código do país).", example = "11998765432")
    private String telefone;

    @Schema(description = "Endereço completo do cliente.", example = "Rua das Flores, 123, São Paulo, SP")
    private String endereco;

    @DecimalMin(value = "0", inclusive = false, message = "O valor não pode ser negativo.")
    @Schema(description = "Saldo atual do cliente. Não pode ser negativo.", example = "1500.75")
    private BigDecimal saldo;
}
