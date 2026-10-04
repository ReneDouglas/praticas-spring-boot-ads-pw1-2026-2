package br.edu.ifpb.notificacaosinan.services;

import br.edu.ifpb.notificacaosinan.dtos.notificacao.CamposDeDuplicidade;
import br.edu.ifpb.notificacaosinan.dtos.notificacao.FiltrosNotificacao;
import br.edu.ifpb.notificacaosinan.dtos.notificacao.Notificacao;
import br.edu.ifpb.notificacaosinan.dtos.notificacao.enums.Autoctone;
import br.edu.ifpb.notificacaosinan.dtos.notificacao.enums.CriterioConfirmacao;
import br.edu.ifpb.notificacaosinan.dtos.utils.FiltrosPaginacao;
import br.edu.ifpb.notificacaosinan.dtos.utils.Pagina;
import br.edu.ifpb.notificacaosinan.utils.Result;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class NotificacaoService {

    private final ConcurrentHashMap<Long, Notificacao> notificacoes;

    public NotificacaoService() {
        this.notificacoes = new ConcurrentHashMap<>();
    }

    public Result<Notificacao, List<String>> cadastrar(Notificacao notificacao) {

        var erros = validarPreenchimento(notificacao);

        if (notificacoes.containsKey(notificacao.numero())) {
            erros.add("Número de notificação já cadastrado.");
        }

        if (!erros.isEmpty()) {
            return new Result.Error<>(erros);
        }

        var duplicidade = notificacoes.putIfAbsent(
                notificacao.numero(),
                notificacao
        );

        if (duplicidade != null) {
            erros.add("Número de notificação já cadastrado.");
            return new Result.Error<>(erros);
        }

        return new Result.Ok<>(notificacao);
    }

    public Result<Notificacao, List<String>> atualizar(Notificacao notificacao, Long numero) {

        if (!Objects.equals(numero, notificacao.numero())){
            return new Result.Error<>(List.of("O número da notificação não pode ser alterado."));
        }

        if (!notificacoes.containsKey(notificacao.numero())) {
            return new Result.Error<>(List.of("Número da notificação não encontrado."));
        }

        var erros = validarPreenchimento(notificacao);

        if (!erros.isEmpty()) {
            return new Result.Error<>(erros);
        }

        var resultado = notificacoes.replace(numero, notificacao);

        if (resultado == null) {
            return new Result.Error<>(List.of("Notificação não encontrada."));
        }
        return new Result.Ok<>(resultado);

    }

    public Result<Notificacao, String> buscarPorNumero(Long numero) {

        Notificacao notificacao = notificacoes.get(numero);

        if (notificacao == null) {
            return new Result.Error<>("Notificação não encontrada.");
        }
        return new Result.Ok<>(notificacao);
    }

    public Pagina listar(
            FiltrosPaginacao paginacao,
            FiltrosNotificacao filtros
    ) {
        int inicio = (paginacao.pagina() - 1) * paginacao.qtde();

        List<Notificacao> listaPaginada = notificacoes
                .values()
                .stream()
                .filter(n -> comparar(filtros.agravo(), n.dadosGerais().agravo()))
                .filter(n -> comparar(filtros.nome(), n.paciente().nome()))
                .filter(n -> comparar(filtros.dataNotificacao(), n.dadosGerais().dataNotificacao()))
                .filter(n -> comparar(filtros.ufResidencia(), n.dadosDeResidencia().uf()))
                .filter(n -> comparar(filtros.munResidencia(), n.dadosDeResidencia().municipio()))
                .filter(n -> comparar(filtros.cFinal(), n.conclusao().classificacaoFinal()))
                .filter(n -> comparar(filtros.criterio(), n.conclusao().criterioDeConfirmacao()))
                .filter(n -> comparar(filtros.autoctone(), n.conclusao().autoctone()))
                .filter(n -> comparar(filtros.evolucao(), n.conclusao().evolucaoCaso()))
                .sorted(ordenador(paginacao.ordem(), paginacao.ordenarPor()))
                .skip(inicio)
                .limit(paginacao.qtde())
                .toList();

        return new Pagina(listaPaginada, paginacao, filtros);
    }

    public Result<Void, String> deletar(Long numero) {

        var resultado = notificacoes.remove(numero);

        if (resultado == null) {
            return new Result.Error<>("Notificação não encontrada.");
        }
        return new Result.Ok<>(null);
    }

    public List<Notificacao> listarDuplicidades() {

        Map<CamposDeDuplicidade, List<Notificacao>> duplicidades =
                notificacoes
                        .values()
                        .stream()
                        .collect(Collectors.groupingBy(n ->
                                new CamposDeDuplicidade(
                                        n.dadosGerais().agravo(),
                                        n.paciente().nome(),
                                        n.paciente().dataNascimento(),
                                        n.paciente().nomeMae(),
                                        n.dadosGerais().dataNotificacao()
                                )));

        return duplicidades.values()
                .stream()
                .filter(grupo -> grupo.size() > 1)
                .flatMap(List<Notificacao>::stream)
                .toList();

    }

    private String primeiroUltimoNome(String nome) {
        List<String> partes = List.of(nome.strip().split("\\s+"));
        if (partes.size() == 1) {
            return partes.getFirst();
        }
        return partes.getFirst() + " " + partes.getLast();
    }

    private List<String> validarPreenchimento(Notificacao notificacao) {

        var erros = new ArrayList<String>();

        if (notificacao.conclusao().uf() == null && isConfirmadoNoBrasilNaoAutoctone(notificacao)) {
            erros.add("UF de conclusão é obrigatória para casos confirmados no Brasil e não autoctones.");
        }

        if (notificacao.conclusao().pais() == null && isConfirmadoNoBrasilNaoAutoctone(notificacao)) {
            erros.add("País de conclusão é obrigatório para casos confirmados no Brasil e não autoctones.");
        }

        if (notificacao.conclusao().pais() == null && isConfirmadoNoExteriorNaoAutoctone(notificacao)) {
            erros.add("País de conclusão é obrigatório para casos confirmados no exterior e não autoctones.");
        }

        return erros;
    }

    private Comparator<Notificacao> ordenador(String ordem, String campoOrdem){

        Comparator<Notificacao> ordenacao =  switch(campoOrdem) {
            case "agravo" -> Comparator.comparing(
                    n -> n.dadosGerais().agravo(),
                    String.CASE_INSENSITIVE_ORDER
            );
            case "nome" -> Comparator.comparing(
                    n -> n.paciente().nome(),
                    String.CASE_INSENSITIVE_ORDER
            );
            case "dataNotificacao" -> Comparator.comparing(
                    n -> n.dadosGerais().dataNotificacao()
            );
            case "ufResidencia" -> Comparator.comparing(
                    n -> n.dadosDeResidencia().uf()
            );
            case "munResidencia" -> Comparator.comparing(
                    n -> n.dadosDeResidencia().municipio(),
                    String.CASE_INSENSITIVE_ORDER
            );
            case "cFinal" -> Comparator.comparing(
                    n -> n.conclusao().classificacaoFinal()
            );
            case "criterio" -> Comparator.comparing(
                    n -> n.conclusao().criterioDeConfirmacao()
            );
            case "autoctone" -> Comparator.comparing(
                    n -> n.conclusao().autoctone()
            );
            case "evolucao" -> Comparator.comparing(
                    n -> n.conclusao().evolucaoCaso()
            );
            default -> {
                yield Comparator.comparing(Notificacao::numero);
            }
        };

        if ("DESC".equalsIgnoreCase(ordem)) {
            return ordenacao.reversed();
        }
        return ordenacao;
    }

    private <T> boolean comparar(T filtro, T valor) {
        if (valor instanceof String) {
            return filtro == null || ((String) valor).equalsIgnoreCase((String) filtro);
        }
        return filtro == null || Objects.equals(filtro, valor);
    }

    private boolean isConfirmadoNoBrasilNaoAutoctone(Notificacao notificacao) {
        return notificacao.dadosDeResidencia().isResidenteNoBrasil()
                && notificacao.conclusao().criterioDeConfirmacao() == CriterioConfirmacao.CODIGO_1
                && notificacao.conclusao().autoctone() == Autoctone.CODIGO_2;
    }

    private boolean isConfirmadoNoExteriorNaoAutoctone(Notificacao notificacao) {
        return !notificacao.dadosDeResidencia().isResidenteNoBrasil()
                && notificacao.conclusao().criterioDeConfirmacao() == CriterioConfirmacao.CODIGO_1
                && notificacao.conclusao().autoctone() == Autoctone.CODIGO_2;
    }

}
