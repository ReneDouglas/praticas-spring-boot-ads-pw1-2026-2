package br.edu.ifpb.notificacaosinan.dtos.notificacao;

import br.edu.ifpb.notificacaosinan.dtos.notificacao.enums.UnidadeFederativa;
import br.edu.ifpb.notificacaosinan.dtos.notificacao.enums.Zona;
import jakarta.validation.constraints.*;

public record DadosDeResidencia(
        UnidadeFederativa uf,

        @Size(max = 100, message = "O município não pode exceder 100 caracteres.")
        String municipio,

        @Size(max = 100, message = "O distrito não pode exceder 100 caracteres.")
        String distrito,

        @Size(max = 100, message = "O bairro não pode exceder 100 caracteres.")
        String bairro,

        @Size(max = 100, message = "O logradouro não pode exceder 100 caracteres.")
        String logradouro,

        @Size(max = 20, message = "O número não pode exceder 20 caracteres.")
        String numero,

        @Size(max = 30, message = "O complemento não pode exceder 30 caracteres.")
        String complemento,

        @Size(max = 100, message = "O ponto de referência não pode exceder 100 caracteres.")
        String pontoReferencia,

        @Pattern(
                regexp = "[0-9]{5}-[0-9]{3}",
                message = "O CEP deve seguir o formato XXXXX-XXX"
        )
        String cep,

        @Pattern(
                regexp = "\\([0-9]{2}\\) [0-9]{5}-[0-9]{4}",
                message = "O telefone deve seguir o formato (XX) XXXXX-XXXX"
        )
        String telefone,
        Zona zona,

        @Size(max = 100, message = "O país não pode exceder 100 caracteres.")
        String pais
) {

    @AssertTrue(
            message = "A UF e o município devem ser informados quando residente no Brasil." +
                    " Caso contrário, o país deve ser informado."
    )
    public boolean isResidenteNoBrasil() {

        boolean residenteNoBrasil =
                pais == null || pais.isBlank() || "Brasil".equalsIgnoreCase(pais);

        if(residenteNoBrasil) {
            return uf != null && !municipio.isBlank();
        }
        return false;
    }
}
