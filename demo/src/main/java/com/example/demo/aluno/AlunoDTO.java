package com.example.demo.aluno;

public record AlunoDTO(Integer matricula,
                       String nome,
                       String curso,
                       EnderecoDTO endereco) {
}
