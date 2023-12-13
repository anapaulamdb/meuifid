package com.example.meuid.bean;

public class Aluno {
    String nome;
    String cpf;
    String curso;
    String email;
    String senha;
    byte[] imagem;
    Integer matricula;
    Integer id;
    Integer aprovado;

    public Aluno(){}
    public Aluno(String nome, String cpf, String curso, Integer matricula, String email, String senha, String imagem, Integer id, Integer aprovado) {}


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

   public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[]  getImagem() {
        return imagem;
    }

    public void setImagem(byte[]  imagem) {
        this.imagem = imagem;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getAprovado() {
        return aprovado;
    }

    public void setAprovado(Integer aprovado) {
        this.aprovado = aprovado;
    }
}
