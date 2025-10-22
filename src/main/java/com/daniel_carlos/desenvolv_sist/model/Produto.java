package com.daniel_carlos.desenvolv_sist.model;

import java.util.Date;

public class Produto {
    private int idProduto;
    private String nome;
    private String descricao;
    private String codBarras;
    private double preco;
    private int estoque;
    private Date dataCadastro;
    private Date dataAlteracao;

    // Construtor vazio
    public Produto() {
    }

    // Construtor com argumentos
    public Produto(int idProduto, String nome, String descricao, String codBarras, double preco, int estoque, Date dataCadastro, Date dataAlteracao) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.codBarras = codBarras;
        this.preco = preco;
        this.estoque = estoque;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
    }

    // Getters e Setters
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarra) {
        this.codBarras = codBarra;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
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
