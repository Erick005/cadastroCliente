package com.agibank.teste.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Representa um erro retornado pela API.")
public class ErroDTO {

    @Schema(description = "Mensagem de erro.", example = "Cliente não encontrado.")
    private String message;

    public ErroDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
