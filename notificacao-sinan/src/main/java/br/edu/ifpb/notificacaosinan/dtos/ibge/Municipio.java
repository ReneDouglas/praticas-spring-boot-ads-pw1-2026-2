package br.edu.ifpb.notificacaosinan.dtos.ibge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Municipio(
        Long id,
        String nome
) {
}
