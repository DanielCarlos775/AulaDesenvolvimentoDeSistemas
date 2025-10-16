package com.daniel_carlos.desenvolv_sist.model;

import java.util.Date;

public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private String cargo;
    private String salario;
    private String usuario;
    private String senha;
    private Date dataCadastro;
    private Date dataAlteracao;

    // Construtores
    public Funcionario(
            int id,
            String nome,
            String cpf,
            String rg,
            String cargo,
            String salario,
            String usuario,
            String senha,
            Date dataCadastro,
            Date dataAlteracao
            ) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.cargo = cargo;
        this.salario = salario;
        this.usuario = usuario;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
    }


    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

}