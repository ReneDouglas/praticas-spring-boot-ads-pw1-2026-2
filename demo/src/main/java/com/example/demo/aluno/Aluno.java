package com.example.demo.aluno;

public record Aluno(Integer matricula,
                    String nome,
                    String curso,
                    Endereco endereco) {
}
