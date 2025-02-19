package com.agibank.teste.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Classe responsável pela configuração de redirecionamento para o Swagger UI.
 *
 * Quando um usuário acessa a raiz da aplicação, esta classe redireciona automaticamente
 * para a interface Swagger UI, permitindo uma visualização interativa da API.
 */
@Controller
public class SwaggerRedirectConfig {

    /**
     * Método responsável pelo redirecionamento para o Swagger UI.
     *
     * Quando a raiz da aplicação ("/") é acessada, este método redireciona a requisição para
     * o endereço "/swagger-ui.html", onde o Swagger UI está disponível.
     *
     * @return O redirecionamento para o Swagger UI.
     */
    @GetMapping("/")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
