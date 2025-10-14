package com.daniel_carlos.desenvolv_sist.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FormularioController {

    @FXML
    protected Button btCancelar;
    @FXML
    protected Button btEditar;
    @FXML
    protected Button btExcluir;
    @FXML
    protected Button btNovo;
    @FXML
    protected Button btSair;
    @FXML
    protected Button btSalvar;
    @FXML
    protected AnchorPane formCadastro;
    @FXML
    private TextField txfBuscar;
    @FXML
    protected TextField txfCPF;
    @FXML
    protected TextField txfCargo;
    @FXML
    protected TextField txfNomeCompleto;
    @FXML
    protected TextField txfRG;
    @FXML
    protected TextField txfSalario;
    @FXML
    protected TextField txfSenha;
    @FXML
    protected TextField txfUsuario;

    protected int statusForm;

    @FXML
    protected void Sair() {
        try {
            Stage stage = (Stage) btSair.getScene().getWindow(); //Pega a cena da janela atual
            AnchorPane form = (AnchorPane) stage.getScene().lookup("#form"); //Localiza o form

            //Carrega o início do sistema
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/planoFundo.fxml"));
            form.getChildren().setAll(fxml);

            form.setTopAnchor(fxml, 0.0);
            form.setBottomAnchor(fxml, 0.0);
            form.setLeftAnchor(fxml, 0.0);
            form.setRightAnchor(fxml, 0.0);

            stage.setTitle("Sistema | Página Inicial");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void init() {
        carregarDados(null);

        txfBuscar.setOnAction(event -> {
            carregarDados(txfBuscar.getText());
        });

        btSair.setOnAction(event -> {
            Sair();
        });

        btNovo.setOnAction(event -> {
            statusForm = 0;
            emEdicao(true);
            habilitaCampos(true);
            limpaCampos();
        });

        btEditar.setOnAction(event -> {
            statusForm = 1;
            emEdicao(true);
            habilitaCampos(true);
            limpaCampos();
        });

        btSalvar.setOnAction(event -> {
            Salvar();
        });

        btCancelar.setOnAction(event -> {
            carregarDados(null);
        });

        btExcluir.setOnAction(event -> {
            Excluir();
        });

        formCadastro.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(e -> {
                    KeyCode key = e.getCode();

                    switch (key) {
                        case ESCAPE:
                            Sair();
                            break;
                        default:
                            break;
                    }
                });
            }
        });
    }

    protected void habilitaCampos(boolean status) {
        for (javafx.scene.Node node : formCadastro.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).setDisable(!status);
            }
        }
    }

    protected void limpaCampos() {
        for (javafx.scene.Node node : formCadastro.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).clear();
            }
        }
    }

    protected void emEdicao(boolean status) {
        if (!status) {
            statusForm = 0;
        }

        btNovo.setDisable(status);
        btEditar.setDisable(status);
        btExcluir.setDisable(status);
        btCancelar.setDisable(!status);
        btSalvar.setDisable(!status);

    }

    protected void carregarDados(String id) {

    }

    protected void Salvar() {

    }

    protected void Excluir() {

    }


}
