package com.agibank.teste.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do ObjectMapper para a aplicação.
 *
 * Esta classe é responsável por configurar o {@link ObjectMapper}, que é utilizado para
 * serializar e desserializar objetos JSON na aplicação. A configuração inclui o
 * registro do módulo {@link JavaTimeModule}, que permite o correto manuseio de objetos
 * relacionados a datas e horários, como {@link java.time.LocalDate}, {@link java.time.LocalDateTime},
 * entre outros tipos da API de data e hora do Java 8+.
 */
@Configuration
public class ObjectMapperConfig {

    /**
     * Método que retorna uma instância configurada de {@link ObjectMapper}.
     *
     * Este método configura o {@link ObjectMapper} para incluir o módulo {@link JavaTimeModule},
     * permitindo que a aplicação trate corretamente tipos de dados temporais (como datas e horas)
     * durante a serialização e desserialização de objetos JSON.
     *
     * @return Uma instância de {@link ObjectMapper} configurada com o módulo {@link JavaTimeModule}.
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
