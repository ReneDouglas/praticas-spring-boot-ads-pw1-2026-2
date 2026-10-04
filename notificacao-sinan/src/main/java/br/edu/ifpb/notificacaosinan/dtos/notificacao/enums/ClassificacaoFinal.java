package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ClassificacaoFinal {
    CODIGO_1("Confirmado"),
    CODIGO_2("Descartado");

    private final String descricao;

    ClassificacaoFinal(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }

}
