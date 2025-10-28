package com.daniel_carlos.desenvolv_sist.controller;

import com.daniel_carlos.desenvolv_sist.dao.FuncionarioDAO;
import com.daniel_carlos.desenvolv_sist.model.Funcionario;
import com.daniel_carlos.desenvolv_sist.util.Metodo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Date;
import java.util.List;

public class FuncionarioController extends FormularioController {

    @FXML
    private TextField txfCPF;

    @FXML
    private TextField txfCargo;

    @FXML
    private TextField txfNomeCompleto;

    @FXML
    private TextField txfRG;

    @FXML
    private TextField txfSalario;

    @FXML
    private TextField txfSenha;

    @FXML
    private TextField txfUsuario;

    @FXML
    private TableColumn<Funcionario, String> colDescricao;

    @FXML
    private TableColumn<Funcionario, Integer> colId;

    @FXML
    private TableView<Funcionario> tabDados;

    private ObservableList<Funcionario> funcionarioList;

    @FXML
    public void initialize() {
        super.init();

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getId()).asObject());

        colDescricao.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getNome()));

        carregarDados(null);

        tabDados.getSelectionModel().selectedItemProperty().addListener(
                (obs, selecao, novaSelecao) -> {
                    if (novaSelecao != null) {
                        txfNomeCompleto.setText(novaSelecao.getNome());
                        txfCPF.setText(novaSelecao.getCpf());
                        txfRG.setText(novaSelecao.getRg());
                        txfCargo.setText(novaSelecao.getCargo());
                        txfSalario.setText(novaSelecao.getSalario());
                        txfUsuario.setText(novaSelecao.getUsuario());
                        txfSenha.setText(novaSelecao.getSenha());
                        //txfDataCadastro.setText(novaSelecao.getDataCadastro().toString());
                    }
                });
    }

    protected void carregarDados(String desc) {
        emEdicao(false);
        habilitaCampos(false);

        FuncionarioDAO dao = new FuncionarioDAO();
        List<Funcionario> funcionarios = dao.listarFuncionarios(desc);
        funcionarioList = FXCollections.observableArrayList(funcionarios);
        tabDados.setItems(funcionarioList);

        if (!funcionarioList.isEmpty()) {
            tabDados.getSelectionModel().selectFirst();
            tabDados.getFocusModel();

            Funcionario funcionario = tabDados.getSelectionModel().getSelectedItem();
            if (funcionario != null) {
                txfNomeCompleto.setText(funcionario.getNome());
                txfCPF.setText(funcionario.getCpf());
                txfRG.setText(funcionario.getRg());
                txfCargo.setText(funcionario.getCargo());
                txfSalario.setText(funcionario.getSalario());
                txfUsuario.setText(funcionario.getUsuario());
                txfSenha.setText(funcionario.getSenha());
                //txfDataCadastro.setText(funcionario.getDataCadastro().toString());
            }
        }
    }

    @FXML
    protected void Salvar() {
        FuncionarioDAO dao = new FuncionarioDAO();
        try {
            String nome = txfNomeCompleto.getText();
            String cpf = txfCPF.getText();
            String rg = txfRG.getText();
            String cargo = txfCargo.getText();
            String salario = txfSalario.getText();
            String usuario = txfUsuario.getText();
            String senha = txfSenha.getText();
            Date data = new Date();

            if (statusForm == 1) {
                Funcionario novoFuncionario = new Funcionario(0, nome, cpf, rg, cargo, salario, usuario, senha, data, data);
                boolean ok = dao.inserirFuncionario(novoFuncionario);

                if (ok) {
                    // MENSAGEM DE CADASTRO CONCLUÍDO
                    Metodo.mensagem("Confirmado", null, "Funcionário salvo com sucesso.", "1");
                } else {
                    // MENSAGEM DE ERRO AO CADASTRAR
                    Metodo.mensagem("Erro", null, "Erro ao salvar funcionário.", "3");
                }

            } else if (statusForm == 2) {
                int id = tabDados.getSelectionModel().getSelectedItem().getId();
                Funcionario atualizaFuncionario = new Funcionario(id, nome, cpf, rg, cargo, salario, usuario, senha, null, null);
                boolean ok = dao.atualizarFuncionario(atualizaFuncionario);

                if (ok) {
                    // MENSAGEM DE ATUALIZAÇÃO CONCLUÍDA
                    Metodo.mensagem("Confirmado", null, "Funcionário alterado com sucesso.", "1");
                } else {
                    // MENSAGEM DE ERRO AO ATUALIZAR
                    Metodo.mensagem("Erro", null, "Erro ao alterar funcionário.", "3");
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
        FuncionarioDAO dao = new FuncionarioDAO();
        try {
            int id = tabDados.getSelectionModel().getSelectedItem().getId();
            boolean ok = dao.excluirFuncionario(id);

            if (ok) {
                //MENSAGEM DE EXCLUIDO COM SUCESSO
                Metodo.mensagem("Confirmado", null, "Funcionário excluído com sucesso.", "1");
            } else {
                //MENSAGEM DE ERRO AO EXCLUIR
                Metodo.mensagem("Erro", null, "Erro ao excluir funcionário.", "3");
            }
        } finally {
            carregarDados(null);
        }
    }
}
