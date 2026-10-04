package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UnidadeIdade {
    CODIGO_1("Hora"),
    CODIGO_2("Dia"),
    CODIGO_3("Mês"),
    CODIGO_4("Ano");

    private final String descricao;

    UnidadeIdade(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }
}
