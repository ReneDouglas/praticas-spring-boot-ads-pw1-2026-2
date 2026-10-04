package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Zona {
    CODIGO_1("Urbana"),
    CODIGO_2("Rural"),
    CODIGO_3("Periurbana"),
    CODIGO_9("Outros");

    private final String descricao;

    Zona(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }
}
