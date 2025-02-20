package com.agibank.teste.api;

import com.agibank.teste.api.response.BuscaClienteResponse;
import com.agibank.teste.dto.ClienteRequestDTO;
import com.agibank.teste.dto.ClienteResponseDTO;
import com.agibank.teste.api.request.CriaClienteRequest;
import com.agibank.teste.exception.ClienteJaCadastradoException;
import com.agibank.teste.exception.ClienteNaoEncontradoException;
import com.agibank.teste.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.agibank.teste.modelo.ClienteModeloTeste.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClienteAPI.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void deveCriaClienteComSucesso() throws Exception {
        CriaClienteRequest criaClienteRequest = getCriaClienteRequest();
        ClienteResponseDTO clienteResponseDTO = getClienteResponseDTO();

        when(clienteService.criaCliente(any(ClienteRequestDTO.class))).thenReturn(clienteResponseDTO);

        mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criaClienteRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("89160540010"));
    }

    @Test
    void deveRetornaErroAoCriarClienteComDadosInvalidos() throws Exception {
        ClienteRequestDTO clienteRequestDTODadosFaltando = getClienteRequestDTOAtualizaCliente();

        mockMvc.perform(
                post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequestDTODadosFaltando)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deveRetornaErroAoCriaClienteComCpfDuplicado() throws Exception {
        ClienteRequestDTO clienteRequestDTO = getClienteRequestDTO();

        doThrow(new ClienteJaCadastradoException("Cpf já cadastrado")).when(clienteService).criaCliente(any(ClienteRequestDTO.class));

        mockMvc.perform(
                        post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(clienteRequestDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void deveAtualizaClienteComSucesso() throws Exception {
        ClienteResponseDTO clienteResponseDTO = getClienteResponseDTOAtualizado();
        ClienteRequestDTO clienteRequestDTOAtualizaCliente= getClienteRequestDTOAtualizaCliente();

        when(clienteService.atualizaCliente(anyLong(), any())).thenReturn(clienteResponseDTO);

        mockMvc.perform(
                        put("/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(clienteRequestDTOAtualizaCliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Erick Portugal"))
                .andExpect(jsonPath("$.email").value("erickfarias@gmail.com"))
                .andExpect(jsonPath("$.saldo").value(5000));
    }

    @Test
    void deveRetornaErroAoTentarAtualizaClienteComCpfInvalido() throws Exception {
        ClienteRequestDTO clienteRequestDTOAtualizaCliente= getClienteRequestDTOAtualizaCliente();

        doThrow(new ClienteNaoEncontradoException("Cliente não encontrado. Verifique se os dados fornecidos estão corretos."))
                .when(clienteService).atualizaCliente(anyLong(), any(ClienteRequestDTO.class));


        mockMvc.perform(
                        put("/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(clienteRequestDTOAtualizaCliente)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornaErroAoAtualizaClienteComDadosInvalidos() throws Exception {
        ClienteRequestDTO clienteRequestDTODadosFaltando = getClienteRequestDTODadosErrados();

        mockMvc.perform(
                        put("/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(clienteRequestDTODadosFaltando)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deveRetornaErroAoTentarAtualizaClienteComIdInvalido() throws Exception {
        mockMvc.perform(
                        put("/clientes/1d")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornaInformacoesCorretasAoBuscarClienteComIdValido() throws Exception {
        BuscaClienteResponse buscaClienteResponse = getBuscaClienteResponse();

        mockMvc.perform(
                        get("/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(buscaClienteResponse)))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornaErroAoBuscaClienteComIdInvalido() throws Exception {
        mockMvc.perform(
                        get("/clientes/d1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornaErroAoBuscaClienteComIdValidoMasComClienteInexistente() throws Exception {
        BuscaClienteResponse buscaClienteResponse = getBuscaClienteResponse();

        doThrow(new ClienteNaoEncontradoException("Cliente não encontrado. Verifique se os dados fornecidos estão corretos."))
                .when(clienteService).buscaCliente(anyLong());

        mockMvc.perform(
                        get("/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(buscaClienteResponse)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRemoverInformacoesAoRemoveClienteComIdValido() throws Exception {
        doNothing().when(clienteService).removeCliente(1L);

        mockMvc.perform(
                        delete("/clientes/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornaErroAoRemoveClienteComIdInvalido() throws Exception {
        doNothing().when(clienteService).removeCliente(1L);

        mockMvc.perform(
                        delete("/clientes/1d"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornaErroAoTentarRemoveClienteComIdValidoMasClienteNaoEncontrado() throws Exception {
        doThrow(new ClienteNaoEncontradoException("Cliente não encontrado. Verifique se os dados fornecidos estão corretos."))
                .when(clienteService).removeCliente(anyLong());

        mockMvc.perform(
                        delete("/clientes/1"))
                .andExpect(status().isNotFound());
    }
}