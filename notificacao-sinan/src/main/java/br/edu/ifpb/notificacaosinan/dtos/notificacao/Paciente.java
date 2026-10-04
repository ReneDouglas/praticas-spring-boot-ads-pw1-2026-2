package br.edu.ifpb.notificacaosinan.dtos.notificacao;

import br.edu.ifpb.notificacaosinan.dtos.notificacao.enums.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record Paciente(
        @NotNull(message = "A data dos primeiros sintomas deve ser informada.")
        @PastOrPresent(message = "A data dos primeiros sintomas não pode ser futura.")
        LocalDate dataPrimeirosSintomas,

        @NotBlank(message = "O nome deve ser informado.")
        @Size(max = 255, message = "O nome não pode exceder 255 caracteres.")
        String nome,

        @Past(message = "A data de nascimento não pode ser futura.")
        LocalDate dataNascimento,

        Idade idade,

        @NotNull(message = "O sexo deve ser informado.")
        Sexo sexo,

        Gestante gestante,
        Raca raca,
        Escolaridade escolaridade,

        @Pattern(
                regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{4}",
                message = "O cartão SUS deve seguir o formato XXX-XXXX-XXXX-XXXX"
        )
        String cartaoSUS,

        @Size(max = 255, message = "O nome da mãe não pode exceder 255 caracteres.")
        String nomeMae
) {

    @AssertTrue(
            message = "Informe a data de nascimento ou a idade do paciente."
    )
    public boolean isDataNascimentoOuIdadeInformada() {
        if (dataNascimento != null) {
            return idade == null;
        }
        return idade != null;
    }

    @AssertTrue(
            message = "O campo gestante deve ser informado quando o sexo for feminino."
    )
    public boolean isSexoFeminino(){
        if (Sexo.F == sexo) {
            return gestante != null;
        }
        return true;
    }

    public record Idade(
            Integer valor,
            UnidadeIdade unidade
    ){}
}
