package com.daniel_carlos.desenvolv_sist.controller;

import com.daniel_carlos.desenvolv_sist.dao.UsuarioDAO;
import com.daniel_carlos.desenvolv_sist.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Label lblMensagem;

    @FXML
    protected void onLogin() {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.autenticar(email, senha);

        if (usuario != null) {
          //  lblMensagem.setText("Bem vindo, " + usuario.getNome());
            lblMensagem.setStyle("-fx-text-fill: green;");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(null);
            alert.setContentText("Bem Vindo ao Sistema ");
            alert.showAndWait();

            //abrir a tela principal
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/principal-view.fxml"));
                Scene scene = new Scene(loader.load());

                Stage stage = (Stage) txtEmail.getScene().getWindow(); //pega a janela atual
                stage.setScene(scene);
                stage.setTitle("Formulário Principal");
                stage.centerOnScreen();
                stage.setMaximized(true);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            lblMensagem.setText("Credenciais inválidas.");
            lblMensagem.setStyle("-fx-text-fill: red;");
        }
    }
}
