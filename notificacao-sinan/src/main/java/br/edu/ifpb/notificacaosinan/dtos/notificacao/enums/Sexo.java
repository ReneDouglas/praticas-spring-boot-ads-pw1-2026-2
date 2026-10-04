package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sexo {
    M("Masculino"),
    F("Feminino"),
    I("Ignorado");

    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }
}
