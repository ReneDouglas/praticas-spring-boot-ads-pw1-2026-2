package br.edu.ifpb.notificacaosinan.dtos.utils;

import br.edu.ifpb.notificacaosinan.dtos.notificacao.FiltrosNotificacao;
import br.edu.ifpb.notificacaosinan.dtos.notificacao.Notificacao;

import java.util.List;

public record Pagina(
        List<Notificacao> itens,
        FiltrosPaginacao paginacao,
        FiltrosNotificacao filtros

) {
}
