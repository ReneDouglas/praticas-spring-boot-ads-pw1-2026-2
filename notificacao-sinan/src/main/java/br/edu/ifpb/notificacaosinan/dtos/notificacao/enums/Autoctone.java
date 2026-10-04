package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Autoctone {
    CODIGO_1("Sim"),
    CODIGO_2("Não"),
    CODIGO_3("Indeterminado");

    private final String descricao;

    Autoctone(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }
}
