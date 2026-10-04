package br.edu.ifpb.notificacaosinan.dtos.notificacao;

import br.edu.ifpb.notificacaosinan.dtos.notificacao.enums.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record Conclusao(
        @NotNull(message = "a data da investigação deve ser informada.")
        @PastOrPresent(message = "a data da investigação não pode ser futura.")
        LocalDate dataDaInvestigacao,

        @NotNull(message = "a classificação final deve ser informada.")
        ClassificacaoFinal classificacaoFinal,

        CriterioConfirmacao criterioDeConfirmacao,
        Autoctone autoctone,
        UnidadeFederativa uf,

        @Size(max = 100, message = "O país não pode exceder 100 caracteres.")
        String pais,

        @Size(max = 100, message = "O município não pode exceder 100 caracteres.")
        String municipio,

        @Size(max = 100, message = "O distrito não pode exceder 100 caracteres.")
        String distrito,

        @Size(max = 100, message = "O bairro não pode exceder 100 caracteres.")
        String bairro,

        DoencaTrabalho doencaTrabalho,
        EvolucaoCaso evolucaoCaso,

        @PastOrPresent(message = "A data de óbito não pode ser futura.")
        LocalDate dataObito,

        @PastOrPresent(message = "A data de encerramento não pode ser futura.")
        LocalDate dataEncerramento
) {
    @AssertFalse(
            message = "A data de encerramento deve ser informada quando o critério de confirmação for 'Confirmado' ou 'Descartado'."
    )
    public boolean isDataEncerramentoInformada() {
        return criterioDeConfirmacao == null || dataEncerramento == null;
    }

    @AssertTrue(
            message = "Informar se o caso é autóctone do município de residência se o caso for confirmado."
    )
    public boolean isCasoConfirmadoAutoctoneNaoPreenchido() {
        if (classificacaoFinal == ClassificacaoFinal.CODIGO_1 && autoctone == null) {
            return false;
        }
        return true;
    }

}
