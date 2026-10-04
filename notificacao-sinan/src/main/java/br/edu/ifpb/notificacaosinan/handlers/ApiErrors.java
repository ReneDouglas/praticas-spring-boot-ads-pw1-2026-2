package br.edu.ifpb.notificacaosinan.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.util.*;

@RestControllerAdvice
public class ApiErrors {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail validacao(MethodArgumentNotValidException e) {
        Map<String, List<String>> erros = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                erro -> {
                    erros.computeIfAbsent(
                            erro.getField(),
                            campo -> new ArrayList<>()
                    ).add(erro.getDefaultMessage());
                }
        );

        ProblemDetail problema = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problema.setTitle("Dados Inválidos");
        problema.setDetail("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        problema.setProperty("erros", erros);
        return problema;

    }

    @ExceptionHandler(RestClientException.class)
    ProblemDetail erroAoConsultarServicoExterno(RestClientException excecao) {
        ProblemDetail problema =
                ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);

        problema.setTitle("Serviço externo indisponível");
        problema.setDetail(
                "Não foi possível consultar os municípios no serviço do IBGE. Digite o nome do município."
        );

        return problema;
    }

}
