package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Raca {
    CODIGO_1("Branca"),
    CODIGO_2("Preta"),
    CODIGO_3("Amarela"),
    CODIGO_4("Parda"),
    CODIGO_5("Indígena"),
    CODIGO_9("Ignorado");

    private final String descricao;

    Raca(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }
}
