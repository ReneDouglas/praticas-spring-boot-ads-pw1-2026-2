package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EvolucaoCaso {
    CODIGO_1("Cura"),
    CODIGO_2("Óbito pelo agravo notificado"),
    CODIGO_3("Óbito por outras causas"),
    CODIGO_9("Ignorado");

    private final String descricao;

    EvolucaoCaso(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }
}
