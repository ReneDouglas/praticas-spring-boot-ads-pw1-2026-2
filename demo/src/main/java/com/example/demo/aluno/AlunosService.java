package com.example.demo.aluno;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AlunosService {

    private final AlunoRepository alunoRepository;

    public AlunosService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoDTO listar(String matricula, String curso) {
        Aluno alunoEntity = alunoRepository
                .findByMatriculaAndCurso(Integer.valueOf(matricula), curso)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return alunoEntity.toDTO();
    }

    public AlunoDTO cadastrar(AlunoDTO aluno){
        Aluno alunoEntity = new Aluno();
        alunoEntity.fromDTO(aluno);
        alunoEntity = alunoRepository.save(alunoEntity);

        return alunoEntity.toDTO();
    }
}
