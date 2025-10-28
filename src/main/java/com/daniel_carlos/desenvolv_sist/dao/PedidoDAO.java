package com.daniel_carlos.desenvolv_sist.dao;

import com.daniel_carlos.desenvolv_sist.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PedidoDAO {

    public int criarPedido() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO pedidos (total) VALUES (0)";
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


    public static boolean inserirItemPedido(int pedidoId, String produto, int quantidade, double precoUnitario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO item_pedido (pedido_id, produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedidoId);
            stmt.setString(2, produto);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, precoUnitario);
            //query.executeUpdate();
            int insert = stmt.executeUpdate();

            return insert > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}