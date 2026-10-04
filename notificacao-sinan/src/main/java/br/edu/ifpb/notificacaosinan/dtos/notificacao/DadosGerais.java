package br.edu.ifpb.notificacaosinan.dtos.notificacao;

import br.edu.ifpb.notificacaosinan.dtos.notificacao.enums.TipoNotificacao;
import br.edu.ifpb.notificacaosinan.dtos.notificacao.enums.UnidadeFederativa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record DadosGerais(
        @NotNull(message = "O tipo de notificação deve ser informado.")
        TipoNotificacao tipoNotificacao,

        @NotBlank(message = "O agravo deve ser informado.")
        @Size(max = 255, message = "O agravo não pode exceder 255 caracteres.")
        String agravo,

        @NotNull(message = "A data de notificação deve ser informada.")
        @PastOrPresent(message = "A data de notificação não pode ser futura.")
        LocalDate dataNotificacao,

        @NotNull(message = "A UF deve ser informada.")
        UnidadeFederativa uf,

        @NotBlank(message = "O município deve ser informado.")
        @Size(max = 100, message = "O município não pode exceder 100 caracteres.")
        String municipio,

        @NotBlank(message = "A UBS deve ser informada.")
        @Size(max = 100, message = "A UBS não pode exceder 100 caracteres.")
        String ubs
) {
}
