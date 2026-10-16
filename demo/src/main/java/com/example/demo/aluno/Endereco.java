package com.example.demo.aluno;

import jakarta.persistence.*;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;

    public Endereco() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Endereco fromDTO(EnderecoDTO enderecoDTO) {
        this.setLogradouro(enderecoDTO.logradouro());
        this.setNumero(enderecoDTO.numero());
        this.setBairro(enderecoDTO.bairro());
        this.setCidade(enderecoDTO.cidade());
        return this;
    }
    public EnderecoDTO toDTO() {
        return new EnderecoDTO(
                this.getLogradouro(),
                this.getNumero(),
                this.getBairro(),
                this.getCidade());
    }
}
