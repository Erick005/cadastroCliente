package com.agibank.teste.api;

import com.agibank.teste.api.request.AtualizaClienteRequest;
import com.agibank.teste.api.request.CriaClienteRequest;
import com.agibank.teste.api.response.AtualizaClienteResponse;
import com.agibank.teste.api.response.BuscaClienteResponse;
import com.agibank.teste.api.response.CriaClienteResponse;
import com.agibank.teste.dto.ErroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Cliente API", description = "Controller responsável por gerenciar e fornece endpoints para criação, atualização, remoção e consultas de clientes")
public interface ClienteAPI {

    @Operation(
            summary = "Cria um novo cliente",
            description = "Cria um novo cliente no sistema com os dados fornecidos. O cliente precisa fornecer informações como nome, CPF, email, telefone e saldo inicial.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do cliente a ser criado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CriaClienteRequest.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de criação de cliente",
                                    value = "{\"nome\": \"João Silva\", \"cpf\": \"31724352024\", \"email\": \"joao@email.com\", \"dataNascimento\": \"1990-05-25\", \"telefone\": \"11987654321\", \"endereco\": \"Rua X, 123\", \"saldo\": 1500.00}"
                            )
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Cliente criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CriaClienteResponse.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Exemplo de resposta de cliente criado",
                                            value = "{\"nome\": \"João Silva\", \"cpf\": \"31724352024\", \"email\": \"joao@email.com\", \"dataNascimento\": \"1990-05-25\", \"telefone\": \"11987654321\", \"endereco\": \"Rua X, 123\", \"saldo\": 1500.00}"
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida. Os dados fornecidos não são válidos.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Exemplo de erro de validação",
                                            value = "{\"error\": \"Campo CPF inválido\", \"details\": \"Por favor, insira um CPF válido sem pontos ou traços.\"}"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<CriaClienteResponse> criaCliente(@RequestBody @Valid CriaClienteRequest criaClienteRequest);

    @Operation(
            summary = "Busca um cliente pelo ID",
            description = "Recupera as informações de um cliente com base no ID fornecido. O ID deve ser um número válido.",
            parameters = @Parameter(
                    name = "id",
                    description = "ID do cliente a ser buscado. Deve ser um número único identificado no sistema.",
                    required = true,
                    example = "1"
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Cliente encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BuscaClienteResponse.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Exemplo de resposta de cliente",
                                            value = "{\"nome\": \"João Silva\", \"cpf\": \"31724352024\", \"email\": \"joao@email.com\", \"dataNascimento\": \"1990-05-25\", \"telefone\": \"11987654321\", \"endereco\": \"Rua X, 123\", \"saldo\": 1500.00}"
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado. O cliente com o ID fornecido não existe.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Exemplo de erro 404",
                                            value = "{\"error\": \"Cliente não encontrado\", \"details\": \"O cliente com o ID 1 não existe no sistema.\"}"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<BuscaClienteResponse> buscaCliente(@PathVariable Long id);

    @Operation(
            summary = "Atualiza as informações de um cliente",
            description = "Atualiza os dados de um cliente já existente. O cliente precisa fornecer o ID e os novos dados a serem atualizados.",
            parameters = @Parameter(
                    name = "id",
                    description = "ID do cliente a ser atualizado. Esse ID é necessário para localizar o cliente no sistema.",
                    required = true,
                    example = "1"
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para atualizar o cliente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtualizaClienteRequest.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de atualização de cliente",
                                    value = "{\"nome\": \"João Silva\", \"email\": \"joao@novoemail.com\", \"telefone\": \"11987654321\", \"endereco\": \"Rua X, 456\", \"saldo\": 1800.00}"
                            )
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Cliente atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtualizaClienteResponse.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Exemplo de resposta de atualização",
                                            value = "{\"nome\": \"João Silva\", \"cpf\": \"31724352024\", \"email\": \"joao@novoemail.com\", \"dataNascimento\": \"1990-05-25\", \"telefone\": \"11987654321\", \"endereco\": \"Rua X, 456\", \"saldo\": 1800.00}"
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida. Alguns dados fornecidos são incorretos ou faltam.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado com o ID fornecido.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))
                    )
            }
    )
    ResponseEntity<AtualizaClienteResponse> atualizaCliente(@PathVariable Long id, @RequestBody @Valid AtualizaClienteRequest criarClienteRequest);

    @Operation(
            summary = "Remove um cliente",
            description = "Remove o cliente com base no ID fornecido. O cliente é removido permanentemente do sistema.",
            parameters = @Parameter(
                    name = "id",
                    description = "ID do cliente a ser removido. Esse ID é necessário para localizar e excluir o cliente.",
                    required = true,
                    example = "1"
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "204",
                            description = "Cliente removido com sucesso. O cliente foi excluído do sistema."
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado com o ID fornecido.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))
                    )
            }
    )
    void removeCliente(@PathVariable Long id);
}
