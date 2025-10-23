package com.daniel_carlos.desenvolv_sist.dao;

import com.daniel_carlos.desenvolv_sist.model.Cliente;
import com.daniel_carlos.desenvolv_sist.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    //Listar
    public List<Cliente> listarClientes(String descricao) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;

        List<Cliente> clientes = new ArrayList<Cliente>();

        try {
            conn = ConnectionFactory.getConnection();
            if (conn == null) return clientes;
            String sql = "select *FROM clientes";

            if (descricao != null && !descricao.isEmpty()) {
                sql = "select *FROM clientes WHERE nome LIKE ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + descricao + "%");
            } else {
                stmt = conn.prepareStatement(sql);
            }

            resultado = stmt.executeQuery();

            while (resultado.next()) {
                Cliente cliente = new Cliente(
                        resultado.getInt("id_cliente"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("telefone"),
                        resultado.getString("email"),
                        resultado.getDate("data_cadastro"),
                        resultado.getDate("data_alteracao")
                );
                cliente.setIdCliente(resultado.getInt("id_cliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setDataCadastro(resultado.getDate("data_cadastro"));
                cliente.setDataAlteracao(resultado.getDate("data_alteracao"));
                clientes.add(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    //INSERT
    public boolean inserirCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT clientes " +
                    "(nome, cpf, telefone, email, data_cadastro, data_alteracao) " +
                    "VALUES (?, ?, ?, ?, now(), null)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());

            if (cliente.getCpf() == null) stmt.setNull(2, Types.VARCHAR);
            else stmt.setString(2, cliente.getCpf());

            if (cliente.getTelefone() == null) stmt.setNull(3, Types.VARCHAR);
            else stmt.setString(3, cliente.getTelefone());

            if (cliente.getEmail() == null) stmt.setNull(4, Types.VARCHAR);
            else stmt.setString(4, cliente.getEmail());

            int insert = stmt.executeUpdate();

            return insert > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE

    public boolean atualizarCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "UPDATE clientes " +
                    " set nome = ?, cpf = ?, telefone = ?, email = ?, data_alteracao = now() " +
                    "WHERE id_cliente = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());

            if (cliente.getCpf() == null) stmt.setNull(2, Types.VARCHAR);
            else stmt.setString(2, cliente.getCpf());

            if (cliente.getTelefone() == null) stmt.setNull(3, Types.VARCHAR);
            else stmt.setString(3, cliente.getTelefone());

            if (cliente.getEmail() == null) stmt.setNull(4, Types.VARCHAR);
            else stmt.setString(4, cliente.getEmail());

            stmt.setInt(5, cliente.getIdCliente());

            int update = stmt.executeUpdate();

            return update > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean excluirCliente(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE from clientes where id_cliente = ?";
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
