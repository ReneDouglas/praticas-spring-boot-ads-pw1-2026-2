package br.edu.ifpb.notificacaosinan.controllers;

import br.edu.ifpb.notificacaosinan.dtos.notificacao.FiltrosNotificacao;
import br.edu.ifpb.notificacaosinan.dtos.utils.FiltrosPaginacao;
import br.edu.ifpb.notificacaosinan.dtos.notificacao.Notificacao;
import br.edu.ifpb.notificacaosinan.dtos.utils.Pagina;
import br.edu.ifpb.notificacaosinan.services.NotificacaoService;
import br.edu.ifpb.notificacaosinan.utils.Result;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Notificacao notificacao) {

        var resultado = notificacaoService
                .cadastrar(notificacao);

        switch (resultado) {
            case Result.Ok<Notificacao, List<String>> ok -> {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(ok.value());
            }
            case Result.Error<Notificacao, List<String>> err -> {

                ProblemDetail problema = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
                problema.setTitle("Dados Inválidos");
                problema.setDetail("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
                problema.setProperty("erros", err.error());

                return ResponseEntity
                        .badRequest()
                        .body(problema);
            }
        }
    }

    @GetMapping("/{numero}")
    public ResponseEntity<?> getNotificacao(@PathVariable Long numero) {

        var resultado = notificacaoService.buscarPorNumero(numero);

        switch(resultado){
            case Result.Ok<Notificacao, String> ok -> {
                return ResponseEntity.ok(ok.value());
            }
            case Result.Error<Notificacao, String> err -> {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.error());
            }
        }
    }

    @GetMapping
    public ResponseEntity<Pagina> listagemPaginadaOrdenadaEComFiltros(
            @ModelAttribute FiltrosPaginacao paginacao,
            @ModelAttribute FiltrosNotificacao filtros
    ) {

        var resultado = notificacaoService.listar(
                paginacao,
                filtros
        );

        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @GetMapping("/duplicidades")
    public ResponseEntity<List<Notificacao>> getDuplicidades() {

        var lista = notificacaoService.listarDuplicidades();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PutMapping("/{numero}")
    public ResponseEntity<?> atualizar(@RequestBody @Valid Notificacao notificacao,
                                       @PathVariable Long numero){

        var resultado = notificacaoService.atualizar(notificacao, numero);

        switch(resultado) {
            case Result.Ok<Notificacao, List<String>> ok -> {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ok.value());
            }
            case Result.Error<Notificacao, List<String>> err -> {

                ProblemDetail problema = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
                problema.setTitle("Erro ao atualizar notificação");
                problema.setProperty("erros", err.error());

                return ResponseEntity
                        .badRequest()
                        .body(problema);
            }
        }
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<?> remover(@PathVariable Long numero){

        var resultado = notificacaoService.deletar(numero);

        switch (resultado) {
            case Result.Ok<Void, String> ignored -> {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            case Result.Error<Void, String> e -> {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.error());
            }
        }
    }
}
