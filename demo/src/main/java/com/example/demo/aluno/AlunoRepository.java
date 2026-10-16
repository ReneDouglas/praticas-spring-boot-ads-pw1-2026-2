package com.example.demo.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Optional<Aluno> findByMatriculaAndCurso(Integer matricula, String curso);

    // 2º forma
    @Query("""
        SELECT a
            FROM Aluno a
                WHERE a.matricula = :matricula
                    AND a.curso = :curso
    """)
    Aluno buscarPorMatriculaECurso(Integer matricula, String curso);

    @Query("""
        SELECT a
             FROM Aluno a
             JOIN FETCH a.endereco e
                WHERE e.logradouro = :logradouro
     """)
    Aluno buscarPorLogradouro(String logradouro);
}
