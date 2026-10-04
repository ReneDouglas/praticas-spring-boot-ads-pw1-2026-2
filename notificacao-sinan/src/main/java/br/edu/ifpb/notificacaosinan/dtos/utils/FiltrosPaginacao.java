package br.edu.ifpb.notificacaosinan.dtos.utils;

import jakarta.validation.constraints.*;

public record FiltrosPaginacao(
        @Positive(message = "A página deve ser maior que zero.")
        Integer pagina,

        @Positive(message = "A quantidade de itens da página deve ser maior que zero.")
        @Max(value = 20, message = "O tamanho da página não deve ultrapassar 30 itens.")
        Integer qtde,

        @Pattern(
                regexp = ".*\\S.*",
                message = "O campo de ordenação não pode ser vazio ou conter apenas espaços."
        )
        String ordenarPor,

        @Pattern(
                regexp = ".*\\S.*",
                message = "O tipo de ordenação não pode ser vazio ou conter apenas espaços."
        )
        String ordem
) {
    public FiltrosPaginacao {
        if (pagina == null) {
            pagina = 1;
        }
        if (qtde == null) {
            qtde = 5;
        }
        if (ordenarPor == null) {
            ordenarPor = "numero";
        }
        if (ordem == null) {
            ordem = "ASC";
        }
    }

    @AssertTrue(message = "Tamanho da página invalido.")
    public boolean isQuantidadeValida(){
        return qtde % 5 == 0;
    }
}
