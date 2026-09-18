package com.example.demo.aluno;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AlunosService {

    private final ArrayList<Aluno> alunos;

    public AlunosService(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Aluno listar(String matricula, String curso) {
        for (Aluno aluno : alunos) {
            if (aluno.matricula().toString().equals(matricula)) {
                return  aluno;
            }
        }
        return null;
    }

    public Aluno cadastrar(Aluno aluno){
        alunos.add(aluno);
        return aluno;
    }
}
