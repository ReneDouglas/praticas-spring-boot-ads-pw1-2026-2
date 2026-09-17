package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

    @GetMapping(path = "/{matricula}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String listar(
            @PathVariable String matricula,
            @RequestParam(required = false) String curso
    ) {
        if(matricula.equals("123") &&
                (curso == null || curso.equals("ADS"))){
            return "Aluno listado";
        }
        return "Aluno não encontrado";
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(
            @RequestBody Aluno aluno){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(aluno);
    }
}
