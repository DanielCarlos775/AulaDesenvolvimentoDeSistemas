package com.daniel_carlos.desenvolv_sist.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.daniel_carlos.desenvolv_sist.dao.PedidoDAO;
import com.daniel_carlos.desenvolv_sist.dao.ProdutoDAO;
import com.daniel_carlos.desenvolv_sist.model.Item;
import com.daniel_carlos.desenvolv_sist.model.Produto;
import com.daniel_carlos.desenvolv_sist.util.Metodo;

public class FrenteDeCaixaController {

    @FXML
    private AnchorPane frenteCaixa;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextField txtBusca;
    @FXML
    private Label lblTipoBusca;
    @FXML
    private Label lblPedido;
    private int pedido = 0;
    private boolean pedidoIniciado = false;

    //TABLE VIEW PARA BUSCAR OS PRODUTOS
    @FXML
    private TableView<Produto> tabItem;
    @FXML
    private TableColumn<Produto, String> tabDescricao;
    @FXML
    private TableColumn<Produto, String> tabId;
    private ObservableList<Produto> produtoList;

    //TABLE VIEW PARA INSERIR OS ITENS DO PEDIDO
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
    private ObservableList<Item> itensList;

    private boolean buscaDescricao = false;

    DecimalFormat formatoMoeda = new DecimalFormat("R$ #,##0.00");
    //NumberFormat formatMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    NumberFormat formatMoeda = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));

    @FXML
    private void initialize() {
    	/*newScene.setOnKeyTyped(event -> {
	    if ("*".equals(event.getCharacter())) {
	    	quantidade.requestFocus();
	        //System.out.println("* pressionado");
	        // Aqui você pode executar qualquer ação desejada
	    }
	});*/
        //PREPARA AS COLUNAS DA TABELA DE BUSCA DE PRODUTO PARA RECEBER OS DADOS
        tabId.setCellValueFactory(new PropertyValueFactory<>("CodBarras"));
        tabDescricao.setCellValueFactory(new PropertyValueFactory<>("Nome"));

        //PREPARA AS COLUNAS DA TABELA DE ITENS DO PEDIDO PARA RECEBER OS DADOS
        colCodBarra.setCellValueFactory(new PropertyValueFactory<>("CodBarras"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));

        colValorUn.setCellValueFactory(new PropertyValueFactory<>("PrecoUnitario"));
        colValorTotal.setCellValueFactory(new PropertyValueFactory<>("ValorTotal"));


        //lblPedido.setText(String.valueOf(pedido));
        lblPedido.setText(String.format("%06d", pedido));
        txtQuantidade.setText("1");

        frenteCaixa.sceneProperty().addListener((obs, oldScene, newScene) -> {// Adiciona listener depois que a cena estiver carregada
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    KeyCode key = event.getCode();
                    Stage stage = (Stage) frenteCaixa.getScene().getWindow();

                    switch (key) {
                        case F1:
                            if (!pedidoIniciado) {
                                PedidoDAO dao = new PedidoDAO();
                                pedido = dao.criarPedido();
                                //lblPedido.setText(String.valueOf(pedido));
                                lblPedido.setText(String.format("%06d", pedido));
                                if (pedido > 0) {
                                    pedidoIniciado = true;
                                }
                            } else {
                                Metodo.mensagem("Pedido", null, "Já existe um pedido em aberto", "1");
                            }
                            break;
                        case F2:
                            System.out.println("F2 pressionado");
                            break;
                        case F3:
                            System.out.println("F3 pressionado ");
                            break;
                        case F8:
                            if (pedidoIniciado) {
                                pedidoIniciado = false;
                                pedido = 0;
                            } else {
                                Metodo.mensagem("Atenção", null, "Nenhum pedido iniciado", "2");
                            }
                            break;
                        case F10:
                        case ESCAPE:
                            //System.out.println("Fechando formulário...");
                            stage.close();
                            break;
                        case MULTIPLY: // Teclado numérico (*)
                            txtQuantidade.requestFocus();
                            //System.out.println("Teclado numérico * pressionado");
                            break;
                        default:

                            break;
                    }
                });
            }
        });
/*
        txtQuantidade.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if ("*".equals(event.getCharacter())) {
                event.consume(); // Bloqueia o * dentro do quantidade
            }
        });
 */
        txtQuantidade.setOnAction(e -> {
            if (txtQuantidade.getText().trim().isEmpty()) {
                txtQuantidade.setText("1");
                txtBusca.requestFocus();

            } else {
                txtQuantidade.setText(String.valueOf(Metodo.strToIntDef(txtQuantidade.getText(), 1)));
                //quantidade.setText(Integer.toString(WindowHelper.strToIntDef(quantidade.getText(),1)));
                txtBusca.requestFocus();
            }

        });

        txtBusca.setOnAction(e -> {
            //inserir o produto
            if (txtBusca.getText().equals("*")) {
                txtBusca.setText(null);
                txtQuantidade.requestFocus();
                txtQuantidade.setText("");
                return;
            }
            ;

            if (!pedidoIniciado) {
                Metodo.mensagem("Incluir novo pedido", null, "Aperte 'F1' para iniciar um novo pedido", "1");
            } else {
                String textoBusca = txtBusca.getText().replace("%", "");
                if (!textoBusca.isEmpty()) {
                    //tabItemVisualizacao(true);
                    buscaItem(2);
                    tabItem.getSelectionModel().selectFirst();
                    inserirNovoItem();
                }
            }
        });

        txtBusca.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            if ((key == KeyCode.MULTIPLY) || ("*".equals(event.getText()))) {// Se for o * do teclado numérico
                //System.out.println("Detectado * do teclado numérico no txtBusca");
                event.consume();
                txtQuantidade.requestFocus();
                txtQuantidade.setText("");
            }

            if (txtBusca.getText() == "") {
                buscaDescricao = false;
                tabItemVisualizacao(buscaDescricao);
            }

            if (key == KeyCode.DIGIT5 && event.isShiftDown()) {
                if (!buscaDescricao) {
                    buscaDescricao = true;
                    lblTipoBusca.setText("Descrição Produto");
                    txtBusca.setText("");
                }
            }

            if (key == KeyCode.DOWN) {
                Platform.runLater(() -> tabItem.requestFocus());
            }
                /* if ("*".equals(event.getText())) {// Se for o * do teclado normal (Shift + 8)
                //System.out.println("Detectado * do teclado normal no edtBusca");
                event.consume();
            }*/

        });

        txtBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            if (buscaDescricao) {
                if (newValue.length() >= 3) {
                    tabItemVisualizacao(buscaDescricao);
                    buscaItem(1);
                }
            }
        });


        tabItem.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            if (key == KeyCode.ENTER) {
                if (pedidoIniciado && pedido > 0) {
                    inserirNovoItem();
                }
            }

        });
    }


    private void tabItemVisualizacao(boolean status) {
        tabItem.setVisible(status);
        tabItem.setManaged(status);
        //percentual=status;
        if (status) {
            lblTipoBusca.setText("Descrição Produto");
        } else {
            lblTipoBusca.setText("Código de Barras Produto");
        }
    }

    public void buscaItem(int tipo) {
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.listarProdutos(txtBusca.getText().replace("%", ""), tipo);
        produtoList = FXCollections.observableArrayList(produtos);
        tabItem.setItems(produtoList);
    }

    public void inserirNovoItem() {
        PedidoDAO dao = new PedidoDAO();
        boolean ok = dao.inserirItemPedido(pedido,
                tabItem.getSelectionModel().getSelectedItem().getIdProduto(),
                Integer.valueOf(txtQuantidade.getText()),
                tabItem.getSelectionModel().getSelectedItem().getPreco(),
                tabItem.getSelectionModel().getSelectedItem().getPreco() * Integer.valueOf(txtQuantidade.getText())
        );

        if (ok) {
            List<Item> itens = dao.listarItensPedido(pedido);
            itensList = FXCollections.observableArrayList(itens);
            tabItemPedido.setItems(itensList);
        }
        txtBusca.setText("");
        txtBusca.requestFocus();
        buscaDescricao = false;
        tabItemVisualizacao(false);
    }
}
