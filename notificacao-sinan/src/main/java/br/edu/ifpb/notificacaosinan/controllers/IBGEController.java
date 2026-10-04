package br.edu.ifpb.notificacaosinan.controllers;

import br.edu.ifpb.notificacaosinan.dtos.ibge.Municipio;
import br.edu.ifpb.notificacaosinan.services.IBGEService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ibge")
public class IBGEController {

    private final IBGEService IBGEservice;

    public IBGEController(IBGEService ibgEservice) {
        IBGEservice = ibgEservice;
    }

    @GetMapping("/estados/{sigla}/municipios")
    public ResponseEntity<List<Municipio>> getMunicipiosPorEstado(@PathVariable String sigla) {

        var municipios = IBGEservice.listarMunicipiosPorEstado(sigla);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(municipios);

    }
}
