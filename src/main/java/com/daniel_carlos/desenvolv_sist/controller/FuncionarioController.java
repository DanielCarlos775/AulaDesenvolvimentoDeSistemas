package com.daniel_carlos.desenvolv_sist.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    public void initialize() {
        super.init();
    }

}
