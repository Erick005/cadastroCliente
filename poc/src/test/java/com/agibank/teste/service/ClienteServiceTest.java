package com.agibank.teste.service;

import com.agibank.teste.dto.ClienteRequestDTO;
import com.agibank.teste.dto.ClienteResponseDTO;
import com.agibank.teste.exception.ClienteJaCadastradoException;
import com.agibank.teste.exception.ClienteNaoEncontradoException;
import com.agibank.teste.model.ClienteEntity;
import com.agibank.teste.repository.ClienteRespository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static com.agibank.teste.modelo.ClienteModeloTeste.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    private static final Long CLIENTE_ID = 1l;

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRespository clienteRespository;

    @Mock
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    // Testes para o criaCliente
    @Test
    void deveCriaClienteComSucesso() {
        ClienteRequestDTO clienteRequestDTO = getClienteRequestDTO();
        ClienteEntity clienteEntity = getClienteEntity();
        ClienteResponseDTO clienteResponseDTO = getClienteResponseDTO();

        when(clienteRespository.findByCpf(clienteRequestDTO.getCpf())).thenReturn(Optional.empty());

        when(objectMapper.convertValue(clienteRequestDTO, ClienteEntity.class)).thenReturn(clienteEntity);

        when(clienteRespository.save(any(ClienteEntity.class))).thenReturn(clienteEntity);

        when(objectMapper.convertValue(clienteEntity, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO resultado = clienteService.criaCliente(clienteRequestDTO);

        assertNotNull(resultado);
        assertThat(clienteResponseDTO)
                .usingRecursiveComparison()
                .isEqualTo(resultado);

        verify(clienteRespository).findByCpf(clienteRequestDTO.getCpf());
        verify(clienteRespository).save(any(ClienteEntity.class));
    }

    @Test
    void deveCriaClienteComSucessoSemCamposOpcionais() {
        ClienteRequestDTO clienteRequestDTO = getClienteRequestDTOSemCamposOpcionais();
        ClienteEntity clienteEntity = getClienteEntitySemCamposOpcionais();
        ClienteResponseDTO clienteResponseDTO = getClienteResponseDTOSemCamposOpcionais();

        when(clienteRespository.findByCpf(clienteRequestDTO.getCpf())).thenReturn(Optional.empty());

        when(objectMapper.convertValue(clienteRequestDTO, ClienteEntity.class)).thenReturn(clienteEntity);

        when(clienteRespository.save(any(ClienteEntity.class))).thenReturn(clienteEntity);

        when(objectMapper.convertValue(clienteEntity, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO resultado = clienteService.criaCliente(clienteRequestDTO);

        assertNotNull(resultado);
        assertThat(clienteResponseDTO)
                .usingRecursiveComparison()
                .isEqualTo(resultado);

        verify(clienteRespository).findByCpf(clienteRequestDTO.getCpf());
        verify(clienteRespository).save(any(ClienteEntity.class));
    }

    @Test
    void deveLancaExcecaoQuandoCpfJaExiste() {
        ClienteRequestDTO clienteRequestDTO = getClienteRequestDTO();
        ClienteEntity clienteEntity = getClienteEntity();

        when(clienteRespository.findByCpf(clienteRequestDTO.getCpf())).thenReturn(Optional.of(clienteEntity));

        assertThrows(ClienteJaCadastradoException.class, () -> clienteService.criaCliente(clienteRequestDTO));

        verify(clienteRespository).findByCpf(clienteRequestDTO.getCpf());
        verify(clienteRespository, never()).save(any(ClienteEntity.class));
    }

    @Test
    void deveLancaExcecaoQuandoRepositorioForNulo() {
        ClienteService clienteService = new ClienteService(new ObjectMapper(), null);

        ClienteRequestDTO clienteEntity = getClienteRequestDTO();

        assertThrows(NullPointerException.class, () -> clienteService.criaCliente(clienteEntity));
    }

    // Teste para o AtualizaCliente
    @Test
    void deveAtualizaSomenteOsDadosFornecidos() {
        ClienteEntity clienteEntity = getClienteEntity();
        ClienteEntity clienteEntityAtualizado = getClienteEntityAtualizado();
        ClienteResponseDTO clienteResponseDTO = getClienteResponseDTOAtualizado();
        ClienteRequestDTO atualizaCliente = getClienteRequestDTOAtualizaCliente();

        when(clienteRespository.findById(CLIENTE_ID)).thenReturn(Optional.of(clienteEntity));

        when(clienteRespository.save(clienteEntity)).thenReturn(clienteEntityAtualizado);

        when(objectMapper.convertValue(clienteEntityAtualizado, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO resultado = clienteService.atualizaCliente(CLIENTE_ID, atualizaCliente);

        assertNotNull(resultado);
        assertThat(clienteResponseDTO)
                .usingRecursiveComparison()
                .isEqualTo(resultado);

        assertEquals("Erick Portugal", resultado.getNome());
        assertEquals("erickfarias@gmail.com", resultado.getEmail());
        assertEquals(new BigDecimal(5000), resultado.getSaldo());

        verify(clienteRespository).findById(CLIENTE_ID);
        verify(clienteRespository).save(clienteEntity);
    }

    @Test
    void deveLancaExcecaoQuandoClienteNaoEncontrado() {
        ClienteRequestDTO clienteRequestDTO = getClienteRequestDTO();

        when(clienteRespository.findById(CLIENTE_ID)).thenReturn(null);

        assertThrows(ClienteNaoEncontradoException.class, () -> clienteService.atualizaCliente(CLIENTE_ID, clienteRequestDTO));

        verify(clienteRespository).findById(CLIENTE_ID);
        verify(clienteRespository, never()).save(any(ClienteEntity.class));
    }

    // Teste para o BuscaCliente
    @Test
    void deveRetornaClienteQuandoIdForValido() {
        ClienteEntity clienteEntity = getClienteEntity();
        ClienteResponseDTO clienteResponseDTO = getClienteResponseDTO();

        when(clienteRespository.findById(CLIENTE_ID)).thenReturn(Optional.of(clienteEntity));

        when(objectMapper.convertValue(clienteEntity, ClienteResponseDTO.class)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO resultado = clienteService.buscaCliente(CLIENTE_ID);

        assertNotNull(resultado);
        assertThat(resultado)
                .usingRecursiveComparison()
                .isEqualTo(clienteEntity);

        verify(clienteRespository).findById(CLIENTE_ID);
    }

    @Test
    void deveRetornaErroQuandoClienteForInvalido() {
        ClienteEntity clienteEntity = getClienteEntity();

        when(clienteRespository.findById(CLIENTE_ID)).thenReturn(null);

        ClienteNaoEncontradoException exception = assertThrows(ClienteNaoEncontradoException.class, () -> clienteService.buscaCliente(CLIENTE_ID));

        assertThat(exception)
                .usingRecursiveComparison()
                .isNotEqualTo(clienteEntity);

        verify(clienteRespository).findById(CLIENTE_ID);
        verify(objectMapper, never()).convertValue(clienteEntity, ClienteResponseDTO.class);
    }

    // Teste para RemoveCliente
    @Test
    void deveRemoveClienteQuandoIdForValido() {
        ClienteEntity clienteEntity = getClienteEntity();

        when(clienteRespository.findById(CLIENTE_ID)).thenReturn(Optional.of(clienteEntity));

        clienteService.removeCliente(CLIENTE_ID);

        verify(clienteRespository).delete(clienteEntity);
    }

    // Teste para DeleteCliente
    @Test
    void deveLancarExcecaoQuandoClienteNaoForEncontrado() {
        ClienteEntity clienteEntity = getClienteEntity();

        when(clienteRespository.findById(CLIENTE_ID)).thenReturn(null);

        ClienteNaoEncontradoException exception = assertThrows(ClienteNaoEncontradoException.class, () -> clienteService.removeCliente(CLIENTE_ID));

        assertThat(exception)
                .usingRecursiveComparison()
                .isNotEqualTo(clienteEntity);

        verify(clienteRespository, never()).delete(clienteEntity);
    }
}