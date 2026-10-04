package br.edu.ifpb.notificacaosinan.dtos.notificacao;

import java.time.LocalDate;

public record CamposDeDuplicidade(
        String agravo,
        String nome,
        LocalDate dataNasc,
        String nomeMae,
        LocalDate dataNotificacao
) {
}
