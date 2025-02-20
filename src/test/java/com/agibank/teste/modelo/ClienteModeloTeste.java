package com.agibank.teste.modelo;

import com.agibank.teste.api.request.CriaClienteRequest;
import com.agibank.teste.api.response.BuscaClienteResponse;
import com.agibank.teste.dto.ClienteRequestDTO;
import com.agibank.teste.dto.ClienteResponseDTO;
import com.agibank.teste.model.ClienteEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClienteModeloTeste {

    public static ClienteEntity getClienteEntity() {
       return new ClienteEntity(
                "Erick", "89160540010", "erick@gmail.com",
                LocalDate.of(2000, 12, 15), "11923508202", "Rua A", new BigDecimal(200)
        );
    }

    public static CriaClienteRequest getCriaClienteRequest() {
        return new CriaClienteRequest("Erick", "40571774008", "erick@gmail.com",
                LocalDate.of(2000, 12, 15), "98765432", "Rua X", new BigDecimal(200)
        );
    }

    public static ClienteRequestDTO getClienteRequestDTO() {
        return new ClienteRequestDTO(
                "Erick", "89160540010", "erick@gmail.com",
                LocalDate.of(2000, 12, 15), "993607202", "Rua A", new BigDecimal(200)
        );
    }

    public static ClienteResponseDTO getClienteResponseDTO() {
        return new ClienteResponseDTO(
                "Erick", "89160540010", "erick@gmail.com",
                LocalDate.of(2000, 12, 15), "11923508202", "Rua A", new BigDecimal(200)
        );
    }

    public static ClienteEntity getClienteEntitySemCamposOpcionais() {
        return new ClienteEntity(
                "Erick", "89160540010", "erick@gmail.com",
                LocalDate.of(2000, 12, 15), "11923508202", "Rua A", new BigDecimal(200)
        );
    }


    public static ClienteRequestDTO getClienteRequestDTOSemCamposOpcionais() {
        return new ClienteRequestDTO(
                "Erick", "89160540010", "erick@gmail.com",
                LocalDate.of(2000, 12, 15), null, "Rua A", new BigDecimal(200)
        );
    }

    public static ClienteResponseDTO getClienteResponseDTOSemCamposOpcionais() {
        return new ClienteResponseDTO(
                "Erick", "89160540010", "erick@gmail.com",
                LocalDate.of(2000, 12, 15), "11923508202", "Rua A", new BigDecimal(200)
        );
    }

    public static ClienteRequestDTO getClienteRequestDTOAtualizaCliente() {
        return new ClienteRequestDTO(
                "Erick Portugal", null, "erickfarias@gmail.com",
                null, null,  null, new BigDecimal(5000)
        );
    }

    public static ClienteRequestDTO getClienteRequestDTODadosErrados() {
        return new ClienteRequestDTO(
                "Eri", null, "erickfarias.com",
                null, null,  null, null
        );
    }

    public static ClienteEntity getClienteEntityAtualizado() {
        return new ClienteEntity(
                "Erick Portugal", "89160540010", "erick@gmail.com",
                LocalDate.of(2000, 12, 15), "11923508202", "Rua A", new BigDecimal(200)
        );
    }

    public static ClienteResponseDTO getClienteResponseDTOAtualizado() {
        return new ClienteResponseDTO(
                "Erick Portugal", "89160540010", "erickfarias@gmail.com",
                LocalDate.of(2000, 12, 15), "11923508202", "Rua A", new BigDecimal(5000)
        );
    }

    public static BuscaClienteResponse getBuscaClienteResponse() {
        return new BuscaClienteResponse(
                "Erick Portugal", "89160540010", "erickfarias@gmail.com",
                LocalDate.of(2000, 12, 15), "11923508202", "Rua A", new BigDecimal(5000)
        );
    }
}
