package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CriterioConfirmacao {
    CODIGO_1("Laboratório"),
    CODIGO_2("Clínico-Epidemiológico");

    private final String descricao;

    CriterioConfirmacao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }
}
