package com.daniel_carlos.desenvolv_sist.controller;

import com.daniel_carlos.desenvolv_sist.dao.UsuarioDAO;
import com.daniel_carlos.desenvolv_sist.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnFechar;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblMensagem;

    // Método para fechar o sistema
    public void close() {
        System.exit(0);
    }

    // Método para Login
    public void login() {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        Alert mensagem;

        if (email.equals("admin") && senha.equals("123")) {
            mensagem = new Alert(Alert.AlertType.CONFIRMATION);
            mensagem.setTitle("Confirmação");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Bom Vindo ao Sistema");
            mensagem.showAndWait();

            btnEntrar.getScene().getWindow().hide();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/principal.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Sistema by Daniel Carlos");
                stage.centerOnScreen();
                stage.setMaximized(true);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Erro");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Email ou Senha Incorretos");
            mensagem.showAndWait();
        }
    }


    @FXML
    private void initialize() {
        btnFechar.setOnAction(event -> {
            close();
        });
        btnEntrar.setOnAction(event -> {
            login();
        });
        txtEmail.setOnAction(event -> txtSenha.requestFocus());
        txtSenha.setOnAction(event -> {
            login();
        });
    }

/*    @FXML
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/principal.fxml"));
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
    }*/
}
