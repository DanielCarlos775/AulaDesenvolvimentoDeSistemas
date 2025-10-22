package com.daniel_carlos.desenvolv_sist.dao;

import com.daniel_carlos.desenvolv_sist.model.Produto;
import com.daniel_carlos.desenvolv_sist.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    //Listar
    public List<Produto> listarProdutos(String descricao) {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet resultado = null;

        List<Produto> produtos = new ArrayList<Produto>();

        try {
            conn = ConnectionFactory.getConnection();
            if (conn == null) return produtos;
            String sql = "select *FROM produtos";

            if (descricao != null && !descricao.isEmpty()) {
                sql = "select *FROM produtos WHERE nome LIKE ?";
                query = conn.prepareStatement(sql);
                query.setString(1, "%" + descricao + "%");
            } else {
                query = conn.prepareStatement(sql);
            }

            resultado = query.executeQuery();

            while (resultado.next()) {
                Produto p = new Produto(
                        resultado.getInt("id_produto"),
                        resultado.getString("nome"),
                        resultado.getString("descricao"),
                        resultado.getString("cod_barras"),
                        resultado.getDouble("preco"),
                        resultado.getInt("estoque"),
                        resultado.getDate("data_cadastro"),
                        resultado.getDate("data_alteracao")
                );
                p.setIdProduto(resultado.getInt("id_produto"));
                p.setNome(resultado.getString("nome"));
                p.setDescricao(resultado.getString("descricao"));
                p.setCodBarras(resultado.getString("cod_barras"));
                p.setPreco(resultado.getDouble("preco"));
                p.setEstoque(resultado.getInt("estoque"));
                p.setDataCadastro(resultado.getDate("data_cadastro"));
                p.setDataAlteracao(resultado.getDate("data_alteracao"));
                produtos.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtos;
    }

    //INSERT
    public boolean inserirProduto(Produto p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT produtos " +
                    "(nome, descricao, cod_barras, preco, estoque, data_cadastro, data_alteracao) " +
                    "VALUES (?, ?, ?, ?, ?, now(), null)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getNome());

            if (p.getDescricao() == null) stmt.setNull(2, Types.VARCHAR);
            else stmt.setString(2, p.getDescricao());

            stmt.setString(3, p.getCodBarras());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getEstoque());

            int insert = stmt.executeUpdate();

            return insert > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE

    public boolean atualizarProduto(Produto p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "UPDATE produtos " +
                    " set nome = ?, descricao = ?, cod_barras = ?, preco = ?, estoque = ?, data_alteracao = now() WHERE id_produto = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getNome());

            if (p.getDescricao() == null) stmt.setNull(2, Types.VARCHAR);
            else stmt.setString(2, p.getDescricao());

            stmt.setString(3, p.getCodBarras());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getEstoque());
            stmt.setInt(6, p.getIdProduto());

            int update = stmt.executeUpdate();

            return update > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean excluirProduto(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE from produtos where id_produto = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int delete = stmt.executeUpdate();

            return delete > 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
