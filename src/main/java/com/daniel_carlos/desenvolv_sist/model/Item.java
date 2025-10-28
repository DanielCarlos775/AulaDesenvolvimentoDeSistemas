package com.daniel_carlos.desenvolv_sist.model;

import java.util.Date;

public class Item {

    private int idItem;
    private int idPedido;
    private int idProduto;
    private int quantidade;
    private String codBarras;
    private String descricao;
    private Double precoUnitario;
    private Double desconto;
    private Double valorTotal;
    private Date dataCadastro;
    private Date dataAlteracao;

    public Item(
            int idItem,
            int idPedido,
            int idProduto,
            int quantidade,
            String codBarras,
            String descricao,
            Double precoUnitario,
            Double desconto,
            Double valorTotal,
            Date dataCadastro,
            Date dataAlteracao
    ) {
        this.idItem = idItem;
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.codBarras = codBarras;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
    }

    // GETTERS E SETTERS

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
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
