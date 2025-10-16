package com.daniel_carlos.desenvolv_sist.controller;

import com.daniel_carlos.desenvolv_sist.dao.FuncionarioDAO;
import com.daniel_carlos.desenvolv_sist.model.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
                        txfCargo.setText(novaSelecao.getRg());
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
                txfCargo.setText(funcionario.getRg());
                txfCargo.setText(funcionario.getCargo());
                txfSalario.setText(funcionario.getSalario());
                txfUsuario.setText(funcionario.getUsuario());
                txfSenha.setText(funcionario.getSenha());
                //txfDataCadastro.setText(funcionario.getDataCadastro().toString());
            }
        }
    }
}
