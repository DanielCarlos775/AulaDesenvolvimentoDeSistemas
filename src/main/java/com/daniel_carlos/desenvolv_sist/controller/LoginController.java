package com.daniel_carlos.desenvolv_sist.controller;

import com.daniel_carlos.desenvolv_sist.dao.FuncionarioDAO;
import com.daniel_carlos.desenvolv_sist.util.Metodo;
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
        if (Metodo.mensagemConfirmacao("Fechar Sistema", null, "Deseja fechar o sistema?")) {
            System.exit(0);
        }
    }


    // Método para Login
    public void login() {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        Alert mensagem;

        FuncionarioDAO dao = new FuncionarioDAO();

        if (dao.autenticar(email, senha)) {
            /*mensagem = new Alert(Alert.AlertType.CONFIRMATION);
            mensagem.setTitle("Confirmação");
            mensagem.setHeaderText(null);
            mensagem.setContentText("Bom Vindo ao Sistema");
            mensagem.showAndWait();*/

            Metodo.mensagem("Confirmação", null, "Bem vindo Ao Sistema", "1");

            btnEntrar.getScene().getWindow().hide();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/principal.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                /*CONFIGURA PROPRIEDADES DA TELA OU FORMULÁRIO*/
                stage.setScene(scene);
                stage.setTitle("Sistema by Daniel Carlos");
                stage.centerOnScreen();
                stage.setMaximized(true);

                // EVENTO DE FECHAMENTO
                stage.setOnCloseRequest(e -> {
                    e.consume(); //Impede o fechamento automático
                    //confirmarSaida(primaryStage);
                    if (Metodo.mensagemConfirmacao("Fechar Sistema", null, "Deseja fechar o sistema?")) {
                        stage.close();
                    }
                });
                stage.show(); //Mostra o formulário
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

        /*mensagem = new Alert(Alert.AlertType.ERROR);
        mensagem.setTitle("Erro");
        mensagem.setHeaderText(null);
        mensagem.setContentText("Email ou Senha Incorretos");
        mensagem.showAndWait();*/

            Metodo.mensagem("Erro", null, "Usuário ou Senha Incorretos", "3");
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
