package com.agibank.teste.api;

import com.agibank.teste.api.request.AtualizaClienteRequest;
import com.agibank.teste.api.response.AtualizaClienteResponse;
import com.agibank.teste.api.response.BuscaClienteResponse;
import com.agibank.teste.dto.ClienteRequestDTO;
import com.agibank.teste.dto.ClienteResponseDTO;
import com.agibank.teste.api.request.CriaClienteRequest;
import com.agibank.teste.api.response.CriaClienteResponse;
import com.agibank.teste.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ClienteAPI implements ClienteSwaggerAPI {

    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<CriaClienteResponse> criaCliente(@RequestBody @Valid CriaClienteRequest criaClienteRequest) {

        ClienteRequestDTO requestDTO = objectMapper.convertValue(criaClienteRequest, ClienteRequestDTO.class);

        ClienteResponseDTO criaCliente = clienteService.criaCliente(requestDTO);

        CriaClienteResponse response = objectMapper.convertValue(criaCliente, CriaClienteResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizaClienteResponse> atualizaCliente(@PathVariable Long id, @RequestBody @Valid AtualizaClienteRequest atualizaClienteRequest) {

        ClienteRequestDTO requestDTO = objectMapper.convertValue(atualizaClienteRequest, ClienteRequestDTO.class);

        ClienteResponseDTO atualizaCliente = clienteService.atualizaCliente(id, requestDTO);

        AtualizaClienteResponse response = objectMapper.convertValue(atualizaCliente, AtualizaClienteResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscaClienteResponse> buscaCliente(@PathVariable Long id) {
        ClienteResponseDTO buscaCliente = clienteService.buscaCliente(id);

        BuscaClienteResponse response = objectMapper.convertValue(buscaCliente, BuscaClienteResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public void removeCliente(@PathVariable Long id) {
        clienteService.removeCliente(id);
    }
}
