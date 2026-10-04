package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Gestante {
    CODIGO_1("1º Trimestre"),
    CODIGO_2("2º Trimestre"),
    CODIGO_3("3º Trimestre"),
    CODIGO_4("Idade Gestacional Ignorada"),
    CODIGO_5("Não Gestante"),
    CODIGO_6("Não se Aplica"),
    CODIGO_9("Ignorado");

    private final String descricao;

    Gestante(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }
}
