package com.daniel_carlos.desenvolv_sist.controller;

import com.daniel_carlos.desenvolv_sist.dao.ProdutoDAO;
import com.daniel_carlos.desenvolv_sist.model.Produto;
import com.daniel_carlos.desenvolv_sist.util.Metodo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

        /*colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getIdProduto()).asObject());

        colDescricao.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getNome()));*/

        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("Nome"));

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
        List<Produto> funcionarios = dao.listarProdutos(desc, 1);
        produtoList = FXCollections.observableArrayList(funcionarios);
        tabDados.setItems(produtoList);

        if (!produtoList.isEmpty()) {
            tabDados.getSelectionModel().selectFirst();
            tabDados.getFocusModel();

            Produto produto = tabDados.getSelectionModel().getSelectedItem();
            if (produto != null) {

                /*LocalDate localDate=produto.getDataCadastro().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();*/

                txfNome.setText(produto.getNome());
                txfDescricao.setText(produto.getDescricao());
                txfCodBarras.setText(produto.getCodBarras());
                txfEstoque.setText(String.valueOf(produto.getEstoque()));
                txfPreco.setText(String.valueOf(produto.getPreco()));
                txfDataCadastro.setText(produto.getDataCadastro().toString());
                //dtCadastro.setValue(produto.getDataCadastro().toLocalDate());
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
                    Metodo.mensagem("Confirmado", null, "Produto salvo com sucesso.", "1");
                } else {
                    // MENSAGEM DE ERRO AO CADASTRAR
                    Metodo.mensagem("Erro", null, "Erro ao salvar Produto.", "3");
                }

            } else if (statusForm == 2) {
                int id = tabDados.getSelectionModel().getSelectedItem().getIdProduto();
                Produto atualizarProduto = new Produto(id, nome, descricao, codBarras, preco, estoque, null, null);
                boolean ok = dao.atualizarProduto(atualizarProduto);

                if (ok) {
                    // MENSAGEM DE ATUALIZAÇÃO CONCLUÍDA
                    Metodo.mensagem("Confirmado", null, "Produto alterado com sucesso.", "1");
                } else {
                    // MENSAGEM DE ERRO AO ATUALIZAR
                    Metodo.mensagem("Erro", null, "Erro ao alterar Produto.", "3");
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
                Metodo.mensagem("Confirmado", null, "Produto excluído com sucesso.", "1");
            } else {
                //MENSAGEM DE ERRO AO EXCLUIR
                Metodo.mensagem("Erro", null, "Erro ao excluir o Produto.", "3");
            }
        } finally {
            carregarDados(null);
        }
    }
}
