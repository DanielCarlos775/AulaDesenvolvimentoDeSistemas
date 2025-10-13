package com.daniel_carlos.desenvolv_sist.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrincipalController {

    @FXML
    private AnchorPane conteudo;

    @FXML
    private void initialize() {
    }

    @FXML
    private void abrirPaginaInicial() {
        carregarTela("/view/home.fxml", "Página Principal");
    }

    @FXML
    private void abrirCadastro() {
        carregarTela("/view/formulario.fxml", "Cadastro de Usuários");
    }

    private void carregarTela(String fxmlFile, String tituloFuncionalidade) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource(fxmlFile));
            conteudo.getChildren().clear();
            conteudo.getChildren().add(fxml);

            AnchorPane.setTopAnchor(fxml, 0.0);
            AnchorPane.setBottomAnchor(fxml, 0.0);
            AnchorPane.setLeftAnchor(fxml, 0.0);
            AnchorPane.setRightAnchor(fxml, 0.0);

            Scene scene = conteudo.getScene();

            if (scene != null) {
                Stage stage = (Stage) scene.getWindow();
                stage.setTitle("Sistema Geral " + tituloFuncionalidade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void logout() {
        Stage stageAtual = (Stage) conteudo.getScene().getWindow();
        stageAtual.close();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Stage stageLogin = new Stage();
            stageLogin.setScene(new Scene(root));
            stageLogin.centerOnScreen();
            stageLogin.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
