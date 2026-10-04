package br.edu.ifpb.notificacaosinan.dtos.notificacao;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record Notificacao(
        @NotNull(message = "O número da notificação deve ser informado.")
        @Positive(message = "O número da notificação deve ser um valor positivo.")
        Long numero,
        @Valid DadosGerais dadosGerais,
        @Valid Paciente paciente,
        @Valid DadosDeResidencia dadosDeResidencia,
        @Valid Conclusao conclusao,

        @Size(max = 500, message = "As observações adicionais não podem exceder 500 caracteres.")
        String observacoesAdicionais,

        Investigador investigador
) {
}
