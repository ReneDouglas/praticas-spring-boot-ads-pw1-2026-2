package br.edu.ifpb.notificacaosinan.services;

import br.edu.ifpb.notificacaosinan.dtos.ibge.Municipio;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class IBGEService {

    private final RestClient ibgeApi;

    public IBGEService(RestClient ibgeApi) {
        this.ibgeApi = ibgeApi;
    }

    public List<Municipio> listarMunicipiosPorEstado(String estado) {
        var municipios = ibgeApi
                .get()
                .uri("/estados/{estado}/municipios",estado)
                .retrieve()
                .body(Municipio[].class);
                //.body(new ParameterizedTypeReference<List<Municipio>>() {});

        return municipios == null ? List.of() : List.of(municipios);

    }
}
