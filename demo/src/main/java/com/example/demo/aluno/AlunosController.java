package com.example.demo.aluno;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
@CrossOrigin("http://127.0.0.1:5500")
public class AlunosController {

    private final AlunosService alunosService;

    public AlunosController(
            AlunosService alunosService) {
        this.alunosService = alunosService;
    }

    @GetMapping(path = "/{matricula}")
    public ResponseEntity<Aluno> listar(
            @PathVariable String matricula,
            @RequestParam(required = false) String curso
    ) {
        Aluno aluno = alunosService.listar(matricula, curso);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        }
        return ResponseEntity.notFound().build();

        /***
         * Retorno utilizando streams
         *
        return alunos
                .stream()
                .filter(aluno -> aluno.matricula().toString().equals(matricula))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
         ***/

    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(
            @RequestBody Aluno aluno){
        Aluno novoAluno = alunosService.cadastrar(aluno);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoAluno);
    }

    @PostMapping("/form")
    public ResponseEntity<String> cadastrarForm(
            @ModelAttribute Aluno aluno){
        Aluno novoAluno = alunosService.cadastrar(aluno);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Aluno cadastrado com sucesso!");

    }
}
