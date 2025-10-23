package com.daniel_carlos.desenvolv_sist.controller;

import com.daniel_carlos.desenvolv_sist.dao.ClienteDAO;
import com.daniel_carlos.desenvolv_sist.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Date;
import java.util.List;

public class ClienteController extends FormularioController {

    @FXML
    private TextField txfCPF;

    @FXML
    private TextField txfDataCadastro;

    @FXML
    private TextField txfEmail;

    @FXML
    private TextField txfNomeCompleto;

    @FXML
    private TextField txfTelefone;

    @FXML
    private TableColumn<Cliente, String> colDescricao;

    @FXML
    private TableColumn<Cliente, Integer> colId;

    @FXML
    private TableView<Cliente> tabDados;

    private ObservableList<Cliente> clienteList;


    @FXML
    public void initialize() {
        super.init();

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getIdCliente()).asObject());

        colDescricao.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getNome()));

        carregarDados(null);

        tabDados.getSelectionModel().selectedItemProperty().addListener(
                (obs, selecao, novaSelecao) -> {
                    if (novaSelecao != null) {
                        txfNomeCompleto.setText(novaSelecao.getNome());
                        txfCPF.setText(novaSelecao.getCpf());
                        txfEmail.setText(novaSelecao.getEmail());
                        txfTelefone.setText(novaSelecao.getTelefone());
                    }
                });
    }

    protected void carregarDados(String desc) {
        emEdicao(false);
        habilitaCampos(false);

        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.listarClientes(desc);
        clienteList = FXCollections.observableArrayList(clientes);
        tabDados.setItems(clienteList);

        if (!clienteList.isEmpty()) {
            tabDados.getSelectionModel().selectFirst();
            tabDados.getFocusModel();

            Cliente cliente = tabDados.getSelectionModel().getSelectedItem();
            if (cliente != null) {
                txfNomeCompleto.setText(cliente.getNome());
                txfCPF.setText(cliente.getCpf());
                txfTelefone.setText(cliente.getTelefone());
                txfEmail.setText(cliente.getEmail());
            }
        }
    }

    @FXML
    protected void Salvar() {
        ClienteDAO dao = new ClienteDAO();
        try {
            String nome = txfNomeCompleto.getText();
            String cpf = txfCPF.getText();
            String telefone = txfTelefone.getText();
            String email = txfEmail.getText();
            Date data = new Date();

            if (statusForm == 1) {
                Cliente novoCliente = new Cliente(0, nome, cpf, telefone, email, data, data);
                boolean ok = dao.inserirCliente(novoCliente);

                if (ok) {
                    // MENSAGEM DE CADASTRO CONCLUÍDO
                } else {
                    // MENSAGEM DE ERRO AO CADASTRAR
                }

            } else if (statusForm == 2) {
                int id = tabDados.getSelectionModel().getSelectedItem().getIdCliente();
                Cliente atualizarCliente = new Cliente(id, nome, cpf, telefone, email, null, null);
                boolean ok = dao.atualizarCliente(atualizarCliente);

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
        ClienteDAO dao = new ClienteDAO();
        try {
            int id = tabDados.getSelectionModel().getSelectedItem().getIdCliente();
            boolean ok = dao.excluirCliente(id);

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
