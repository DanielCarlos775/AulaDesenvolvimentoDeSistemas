package com.daniel_carlos.desenvolv_sist.dao;

import com.daniel_carlos.desenvolv_sist.model.Funcionario;
import com.daniel_carlos.desenvolv_sist.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    //Listar
    public List<Funcionario> listarFuncionarios(String descricao) {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet resultado = null;

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        try {
            conn = ConnectionFactory.getConnection();
            if (conn == null) return funcionarios;
            String sql = "select *FROM funcionarios";

            if (descricao != null && !descricao.isEmpty()) {
                sql = "select *FROM funcionarios WHERE nome LIKE ?";
                query = conn.prepareStatement(sql);
                query.setString(1, "%" + sql + "%");
            } else {
                query = conn.prepareStatement(sql);
            }

            resultado = query.executeQuery();

            while (resultado.next()) {
                Funcionario f = new Funcionario(
                        resultado.getInt("id_funcionario"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("rg"),
                        resultado.getString("cargo"),
                        resultado.getString("salario"),
                        resultado.getString("usuario"),
                        resultado.getString("senha"),
                        resultado.getDate("data_cadastro"),
                        resultado.getDate("data_alteracao")
                );
                f.setId(resultado.getInt("id_funcionario"));
                f.setNome(resultado.getString("nome"));
                f.setCpf(resultado.getString("cpf"));
                f.setRg(resultado.getString("rg"));
                f.setCargo(resultado.getString("cargo"));
                f.setSalario(resultado.getString("salario"));
                f.setUsuario(resultado.getString("usuario"));
                f.setSenha(resultado.getString("senha"));
                f.setDataCadastro(resultado.getDate("data_cadastro"));
                f.setDataAlteracao(resultado.getDate("data_alteracao"));
                funcionarios.add(f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return funcionarios;
    }


    /*public Funcionario autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Funcionario usuario = new Funcionario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setUsuario(rs.getString("usuario"));
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // login falhou
    }*/
}
