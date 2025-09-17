package com.daniel_carlos.desenvolv_sist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 250);
        stage.setScene(scene);
        stage.setTitle("Tela de Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}