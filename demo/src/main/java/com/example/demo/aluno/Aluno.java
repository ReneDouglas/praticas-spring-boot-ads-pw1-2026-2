package com.example.demo.aluno;

import jakarta.persistence.*;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer matricula;
    private String nome;
    private String curso;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    public Aluno(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Aluno fromDTO(AlunoDTO alunoDTO) {
        this.setMatricula(alunoDTO.matricula());
        this.setNome(alunoDTO.nome());
        this.setCurso(alunoDTO.curso());
        Endereco endereco = new Endereco();
        endereco.fromDTO(alunoDTO.endereco());
        this.setEndereco(endereco);
        return this;
    }

    public AlunoDTO toDTO() {
        return new AlunoDTO(
                this.getMatricula(),
                this.getNome(),
                this.getCurso(),
                this.getEndereco().toDTO());
    }
}
