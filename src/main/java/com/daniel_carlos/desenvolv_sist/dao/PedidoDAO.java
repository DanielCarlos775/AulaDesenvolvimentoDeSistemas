package com.daniel_carlos.desenvolv_sist.dao;

import com.daniel_carlos.desenvolv_sist.model.Item;
import com.daniel_carlos.desenvolv_sist.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public int criarPedido() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO pedidos (data_pedido,id_cliente,id_funcionario,desconto,valor_pedido,valor_total,data_cadastro,data_alteracao)" +
                    "VALUES (now(),null,null,0,0,0,now(),null)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // retorna o ID do pedido criado
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0; // retorna 0 se falhar
    }


    public boolean inserirItemPedido(int IdPedido, int IdProduto, int quantidade,
                                     double precoUnitario, double desconto, double valorTotal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO item_pedido (id_pedido, id_produto, quantidade, preco_unitario, desconto, data_cadastro, data_alteracao)" +
                    "VALUES (?, ?, ?, ?, ?, now(), null)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, IdPedido);
            stmt.setInt(2, IdProduto);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, precoUnitario);
            stmt.setDouble(5, desconto);
            //stmt.setDouble(6,valorTotal);
            //query.executeUpdate();
            int insert = stmt.executeUpdate();

            return insert > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> listarItensPedido(int codPedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;

        List<Item> itens = new ArrayList<Item>();
        try {
            conn = ConnectionFactory.getConnection();
            if (conn == null) return itens;

            String sql = "select i.*,p.* from itens_pedido i inner join produtos p on p.id_produto=i.id_produto where i.id_pedido= ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codPedido);


            resultado = stmt.executeQuery();

            while (resultado.next()) {
                Item i = new Item(
                        resultado.getInt("id_item"),
                        resultado.getInt("id_pedido"),
                        resultado.getInt("id_produto"),
                        resultado.getInt("quantidade"),
                        resultado.getString("codBarras"),
                        resultado.getString("Descricao"),
                        resultado.getDouble("preco_unitario"),
                        resultado.getDouble("desconto"),
                        resultado.getDouble("valor_total"),
                        resultado.getDate("data_cadastro"),
                        resultado.getDate("data_alteracao")
                );
                i.setIdItem(resultado.getInt("id_item"));
                i.setIdPedido(resultado.getInt("id_pedido"));
                i.setIdProduto(resultado.getInt("id_produto"));
                i.setQuantidade(resultado.getInt("quantidade"));
                i.setCodBarras(resultado.getString("codBarras"));
                i.setDescricao(resultado.getString("Descricao"));
                i.setPrecoUnitario(resultado.getDouble("preco_unitario"));
                i.setDesconto(resultado.getDouble("desconto"));
                i.setValorTotal(resultado.getDouble("valor_total"));
                i.setDataCadastro(resultado.getDate("data_cadastro"));
                i.setDataAlteracao(resultado.getDate("data_alteracao"));
                itens.add(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return itens;
    }
}