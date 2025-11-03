package com.daniel_carlos.desenvolv_sist.model;

import java.sql.Timestamp;
import java.util.Date;

public class Pedido {

    private int idPedido;
    private int idFuncionario;
    private int idCliente;
    private double subtotalPedido;
    private double desconto;
    private double totalPedido;
    private Date dataPedido;
    private Date dataCadastro;
    private Date dataAlteracao;

    public Pedido(int idPedido,
                  int idFuncionario,
                  int idCliente,
                  double subtotalPedido,
                  double desconto,
                  double totalPedido,
                  Date dataPedido,
                  Date dataCadastro,
                  Date dataAlteracao
    ) {
        this.idPedido = idPedido;
        this.idFuncionario = idFuncionario;
        this.idCliente = idCliente;
        this.subtotalPedido = subtotalPedido;
        this.desconto = desconto;
        this.totalPedido = totalPedido;
        this.dataPedido = dataPedido;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getSubtotalPedido() {
        return subtotalPedido;
    }

    public void setSubtotalPedido(double subtotalPedido) {
        this.subtotalPedido = subtotalPedido;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
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
