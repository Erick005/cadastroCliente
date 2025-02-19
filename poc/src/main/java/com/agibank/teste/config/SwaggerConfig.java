package com.agibank.teste.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do Swagger para a API Agibank.
 *
 * Esta classe configura o Swagger para gerar a documentação interativa da API,
 * incluindo informações sobre o título, versão, descrição e detalhes de contato do responsável pela API.
 *
 * A configuração é realizada através de um bean do tipo {@link OpenAPI}, que define as informações gerais
 * da API, como o nome, a versão, a descrição e o contato com o desenvolvedor.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Método responsável por configurar o OpenAPI (Swagger) para a documentação da API.
     *
     * Este método cria um objeto {@link OpenAPI} que define as informações gerais da API, como:
     * - Título: "API Agibank"
     * - Versão: "v1"
     * - Descrição: Descrição detalhada das funcionalidades da API para gerenciamento de clientes bancários.
     * - Informações de contato do desenvolvedor responsável pela API.
     *
     * @return Um objeto {@link OpenAPI} configurado com as informações da API.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Agibank")
                        .version("v1")
                        .description("A API RESTful desenvolvida tem como objetivo o gerenciamento de clientes de um banco, oferecendo funcionalidades para realizar o cadastro, consulta, atualização e remoção de dados dos clientes. A aplicação assegura que os dados sejam validados em todos os pontos de entrada, como CPF, e-mail e saldo, garantindo a integridade das informações. A segurança e a manutenção da API também foram priorizadas, com validações de dados e tratamento de exceções para evitar erros e inconsistências.")
                        .contact(new Contact().name("Erick Portugal").email("erickdev@gmail.com")));
    }
}
