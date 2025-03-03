package com.agibank.teste.service;

import com.agibank.teste.dto.ClienteRequestDTO;
import com.agibank.teste.dto.ClienteResponseDTO;
import com.agibank.teste.exception.ClienteJaCadastradoException;
import com.agibank.teste.exception.ClienteNaoEncontradoException;
import com.agibank.teste.model.ClienteEntity;
import com.agibank.teste.repository.ClienteRespository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ObjectMapper objectMapper;

    private final ClienteRespository clienteRespository;

    public ClienteResponseDTO criaCliente(ClienteRequestDTO requestDTO) {
            Optional<ClienteEntity> clienteEntityCpf = clienteRespository.findByCpf(requestDTO.getCpf());

            if (clienteEntityCpf.isPresent()) {
                throw new ClienteJaCadastradoException("Cpf já cadastrado");
            }

            ClienteEntity clienteEntity = objectMapper.convertValue(requestDTO, ClienteEntity.class);

            ClienteEntity salvaCliente = clienteRespository.save(clienteEntity);

            return objectMapper.convertValue(salvaCliente, ClienteResponseDTO.class);
    }

    public ClienteResponseDTO atualizaCliente(Long id, ClienteRequestDTO requestDTO) {

        final var clienteEntity = buscaClientePorId(id);

        if (Objects.nonNull(requestDTO.getNome())) clienteEntity.setNome(requestDTO.getNome());
        if (Objects.nonNull(requestDTO.getCpf())) clienteEntity.setCpf(requestDTO.getCpf());
        if (Objects.nonNull(requestDTO.getEmail())) clienteEntity.setEmail(requestDTO.getEmail());
        if (Objects.nonNull(requestDTO.getDataNascimento())) clienteEntity.setDataNascimento(requestDTO.getDataNascimento());
        if (Objects.nonNull(requestDTO.getTelefone())) clienteEntity.setTelefone(requestDTO.getTelefone());
        if (Objects.nonNull(requestDTO.getEndereco())) clienteEntity.setEndereco(requestDTO.getEndereco());
        if (Objects.nonNull(requestDTO.getSaldo())) clienteEntity.setSaldo(requestDTO.getSaldo());

        ClienteEntity atualizarCliente = clienteRespository.save(clienteEntity);

        return objectMapper.convertValue(atualizarCliente, ClienteResponseDTO.class);
    }

    public ClienteResponseDTO buscaCliente(Long id) {

        final var buscaClientePorId = buscaClientePorId(id);

        return objectMapper.convertValue(buscaClientePorId, ClienteResponseDTO.class);
    }

    public void removeCliente(Long id) {
        final var buscaCliente = buscaClientePorId(id);

        clienteRespository.delete(buscaCliente);
    }

    private ClienteEntity buscaClientePorId(Long id) {
        return clienteRespository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado. Verifique se os dados fornecidos estão corretos."));
    }
}
