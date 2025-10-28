package com.daniel_carlos.desenvolv_sist.controller;

import com.daniel_carlos.desenvolv_sist.model.Item;
import com.daniel_carlos.desenvolv_sist.model.Produto;
import com.daniel_carlos.desenvolv_sist.util.Metodo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class FrenteDeCaixaController {

    @FXML
    private AnchorPane frenteCaixa;
    @FXML
    private TextField quantidade;
    @FXML
    private TextField buscarProduto;
    @FXML
    private Label lblTipoBusca;
    @FXML
    private TableView<Produto> tabItem;
    @FXML
    private TableColumn<Produto, String> tabDescricao;
    @FXML
    private TableColumn<Produto, String> tabID;
    private ObservableList<Produto> produtoList;

    @FXML
    private TableView<Item> tabItemPedido;
    @FXML
    private TableColumn<Item, Integer> colQuantidade;
    @FXML
    private TableColumn<Item, String> colCodBarra;
    @FXML
    private TableColumn<Item, String> colDescricao;
    @FXML
    private TableColumn<Item, Double> colValorTotal;
    @FXML
    private TableColumn<Item, Double> colValorUn;
    private ObservableList<Item> itemList;

    private boolean buscaDescricao = false;

    @FXML
    private void initialize() {
    	/*newScene.setOnKeyTyped(event -> {
	    if ("*".equals(event.getCharacter())) {
	    	quantidade.requestFocus();
	        //System.out.println("* pressionado");
	        // Aqui você pode executar qualquer ação desejada
	    }
	});*/

        tabID.setCellValueFactory(new PropertyValueFactory<>("CodBarra"));
        tabDescricao.setCellValueFactory(new PropertyValueFactory<>("Nome"));

        colCodBarra.setCellValueFactory(new PropertyValueFactory<>("CodBarras"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
        colValorUn.setCellValueFactory(new PropertyValueFactory<>("PrecoUnitario"));
        colValorTotal.setCellValueFactory(new PropertyValueFactory<>("ValorTotal"));


        quantidade.setText("1");

        frenteCaixa.sceneProperty().addListener((obs, oldScene, newScene) -> {// Adiciona listener depois que a cena estiver carregada
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    KeyCode key = event.getCode();
                    Stage stage = (Stage) frenteCaixa.getScene().getWindow();

                    switch (key) {
                        case F1:
                            System.out.println("F1 pressionado ");
                            break;
                        case F2:
                            System.out.println("F2 pressionado");
                            break;
                        case F3:
                            System.out.println("F3 pressionado ");
                            break;
                        case F10:
                        case ESCAPE:
                            //System.out.println("Fechando formulário...");
                            stage.close();
                            break;
                        case MULTIPLY: // Teclado numérico (*)
                            quantidade.requestFocus();
                            //System.out.println("Teclado numérico * pressionado");
                            //
                            break;
                        default:

                            break;
                    }
                });
            }
        });

        quantidade.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if ("*".equals(event.getCharacter())) {
                event.consume(); // Bloqueia o * dentro do quantidade
            }
        });

        quantidade.setOnAction(e -> {
            if (quantidade.getText().trim().isEmpty()) {
                quantidade.setText("1");
                buscarProduto.requestFocus();

            } else {
                quantidade.setText(String.valueOf(Metodo.strToIntDef(quantidade.getText(), 1)));
                //quantidade.setText(Integer.toString(WindowHelper.strToIntDef(quantidade.getText(),1)));
                buscarProduto.requestFocus();
            }

        });

        buscarProduto.setOnAction(e -> {
            //inserir o produto

            if (buscarProduto.getText().equals("*")) {
                buscarProduto.setText(null);
                quantidade.requestFocus();
                quantidade.setText("");
                return;
            }
            ;


        });

        buscarProduto.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            if ((key == KeyCode.MULTIPLY) || ("*".equals(event.getText()))) {// Se for o * do teclado numérico
                //System.out.println("Detectado * do teclado numérico no buscarProduto");
                event.consume();
                quantidade.requestFocus();
                quantidade.setText("");
            }

            if (buscarProduto.getText() == "") {
                lblTipoBusca.setText("Código de Barras Produto");
                buscaDescricao = false;
                tabItemVisualizacao(buscaDescricao);
            }

            if (key == KeyCode.DIGIT5 && event.isShiftDown()) {
                if (!buscaDescricao) {
                    buscaDescricao = true;
                    lblTipoBusca.setText("Descrição Produto");
                    buscarProduto.setText("");
                }
            }

            buscarProduto.textProperty().addListener((observable, oldValue, newValue) -> {
                if (buscaDescricao) {
                    if (newValue.length() >= 3) {
                        tabItemVisualizacao(buscaDescricao);
                    }
                }
            });

           /* if ("*".equals(event.getText())) {// Se for o * do teclado normal (Shift + 8)
                //System.out.println("Detectado * do teclado normal no buscarProduto");
                event.consume();
            }*/

        });
    }

    private void tabItemVisualizacao(boolean status) {
        tabItem.setVisible(status);
        tabItem.setManaged(status);
        //percentual=status;
        if (status) {
        }
    }


}
