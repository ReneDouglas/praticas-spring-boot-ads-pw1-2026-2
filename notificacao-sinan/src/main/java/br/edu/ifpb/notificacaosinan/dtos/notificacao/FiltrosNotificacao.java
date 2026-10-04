package br.edu.ifpb.notificacaosinan.dtos.notificacao;

import br.edu.ifpb.notificacaosinan.dtos.notificacao.enums.*;

import java.time.LocalDate;

public record FiltrosNotificacao(
        String agravo,
        String nome,
        LocalDate dataNotificacao,
        UnidadeFederativa ufResidencia,
        String munResidencia,
        ClassificacaoFinal cFinal,
        CriterioConfirmacao criterio,
        Autoctone autoctone,
        EvolucaoCaso evolucao,
        Boolean duplicadas
) {
}
