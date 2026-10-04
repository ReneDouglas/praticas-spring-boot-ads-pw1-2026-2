package br.edu.ifpb.notificacaosinan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class IBGEApiConfig {

    @Bean
    public RestClient ibgeRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://servicodados.ibge.gov.br/api/v1/localidades/")
                .build();
    }

}
