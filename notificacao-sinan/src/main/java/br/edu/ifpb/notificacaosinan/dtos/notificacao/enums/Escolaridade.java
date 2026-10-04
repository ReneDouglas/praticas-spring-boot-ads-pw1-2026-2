package br.edu.ifpb.notificacaosinan.dtos.notificacao.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Escolaridade {
    CODIGO_0("Analfabeto"),
    CODIGO_1("1ª a 4ª Série Incompleta do EF"),
    CODIGO_2("4ª Série Incompleta do EF"),
    CODIGO_3("5ª a 8ª Série Completa do EF"),
    CODIGO_4("Ensino Fundamental Completo"),
    CODIGO_5("Ensino Médio Incompleto"),
    CODIGO_6("Ensino Médio Completo"),
    CODIGO_7("Ensino Superior Incompleto"),
    CODIGO_8("Ensino Superior Completo"),
    CODIGO_9("Ignorado"),
    CODIGO_10("Não Se Aplica");

    private final String descricao;

    Escolaridade(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return name();
    }

    public String getDescricao() {
        return descricao;
    }

}
