package com.daniel_carlos.desenvolv_sist.controller;

import com.daniel_carlos.desenvolv_sist.dao.ProdutoDAO;
import com.daniel_carlos.desenvolv_sist.model.Produto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.Date;
import java.util.List;

public class ProdutoController extends FormularioController {

    @FXML
    protected TextField txfDataCadastro;

    @FXML
    protected TextField txfCodBarras;

    @FXML
    protected TextField txfNome;

    @FXML
    protected TextField txfPreco;

    @FXML
    protected TextField txfEstoque;

    @FXML
    protected TextField txfDescricao;

    @FXML
    private TableColumn<Produto, String> colDescricao;

    @FXML
    private TableColumn<Produto, Integer> colId;

    @FXML
    private TableView<Produto> tabDados;

    private ObservableList<Produto> produtoList;

    @FXML
    public void initialize() {
        super.init();

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getIdProduto()).asObject());

        colDescricao.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getNome()));

        carregarDados(null);

        tabDados.getSelectionModel().selectedItemProperty().addListener(
                (obs, selecao, novaSelecao) -> {
                    if (novaSelecao != null) {
                        txfNome.setText(novaSelecao.getNome());
                        txfDescricao.setText(novaSelecao.getDescricao());
                        txfCodBarras.setText(novaSelecao.getCodBarras());
                        txfEstoque.setText(String.valueOf(novaSelecao.getEstoque()));
                        txfPreco.setText(String.valueOf(novaSelecao.getPreco()));
                        txfDataCadastro.setText(novaSelecao.getDataCadastro().toString());
                    }
                });

        Platform.runLater(() -> tabDados.requestFocus());
    }

    protected void carregarDados(String desc) {
        emEdicao(false);
        habilitaCampos(false);

        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> funcionarios = dao.listarProdutos(desc);
        produtoList = FXCollections.observableArrayList(funcionarios);
        tabDados.setItems(produtoList);

        if (!produtoList.isEmpty()) {
            tabDados.getSelectionModel().selectFirst();
            tabDados.getFocusModel();

            Produto produto = tabDados.getSelectionModel().getSelectedItem();
            if (produto != null) {
                txfNome.setText(produto.getNome());
                txfDescricao.setText(produto.getDescricao());
                txfCodBarras.setText(produto.getCodBarras());
                txfEstoque.setText(String.valueOf(produto.getEstoque()));
                txfPreco.setText(String.valueOf(produto.getPreco()));
                txfDataCadastro.setText(produto.getDataCadastro().toString());
            }
        }
    }

    @FXML
    protected void Salvar() {
        ProdutoDAO dao = new ProdutoDAO();
        try {
            String nome = txfNome.getText();
            String descricao = txfDescricao.getText();
            String codBarras = txfCodBarras.getText();
            int estoque = Integer.valueOf(txfEstoque.getText());
            double preco = Double.valueOf(txfPreco.getText());
            Date data = new Date();

            if (statusForm == 1) {
                Produto novoProduto = new Produto(0, nome, descricao, codBarras, preco, estoque, data, data);
                boolean ok = dao.inserirProduto(novoProduto);

                if (ok) {
                    // MENSAGEM DE CADASTRO CONCLUÍDO
                } else {
                    // MENSAGEM DE ERRO AO CADASTRAR
                }

            } else if (statusForm == 2) {
                int id = tabDados.getSelectionModel().getSelectedItem().getIdProduto();
                Produto atualizarProduto = new Produto(id, nome, descricao, codBarras, preco, estoque, null, null);
                boolean ok = dao.atualizarProduto(atualizarProduto);

                if (ok) {
                    // MENSAGEM DE ATUALIZAÇÃO CONCLUÍDA
                } else {
                    // MENSAGEM DE ERRO AO ATUALIZAR
                }

            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            carregarDados(null);
        }
    }

    @FXML
    protected void Excluir() {
        ProdutoDAO dao = new ProdutoDAO();
        try {
            int id = tabDados.getSelectionModel().getSelectedItem().getIdProduto();
            boolean ok = dao.excluirProduto(id);

            if (ok) {
                //MENSAGEM DE EXCLUIDO COM SUCESSO

            } else {
                //MENSAGEM DE ERRO AO EXCLUIR
            }
        } finally {
            carregarDados(null);
        }
    }
}
