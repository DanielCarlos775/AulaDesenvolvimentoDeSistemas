package com.daniel_carlos.desenvolv_sist.dao;

import com.daniel_carlos.desenvolv_sist.model.Funcionario;
import com.daniel_carlos.desenvolv_sist.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // Autenticar Usuário e Senha
    public boolean autenticar(String usuario, String senha) {
        try {
            String sql = "SELECT *from funcionarios where BINARY usuario=? and BINARY senha=?";

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet resultado = stmt.executeQuery();
            return resultado.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


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
                query.setString(1, "%" + descricao + "%");
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

    //INSERT
    public boolean inserirFuncionario(Funcionario funcionario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT funcionarios " +
                    "(nome, cpf, rg, cargo, salario, usuario, senha, data_cadastro, data_alteracao) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, now(), null)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());

            if (funcionario.getRg() == null) stmt.setNull(3, Types.VARCHAR);
            else stmt.setString(3, funcionario.getRg());

            if (funcionario.getCargo() == null) stmt.setNull(4, Types.VARCHAR);
            else stmt.setString(4, funcionario.getCargo());

            if (funcionario.getSalario() == null) stmt.setNull(5, Types.VARCHAR);
            else stmt.setString(5, funcionario.getSalario());

            stmt.setString(6, funcionario.getUsuario());
            stmt.setString(7, funcionario.getSenha());

            int insert = stmt.executeUpdate();

            return insert > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE

    public boolean atualizarFuncionario(Funcionario funcionario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "UPDATE funcionarios " +
                    " set nome = ?, cpf = ?, rg = ?, cargo = ?, salario = ?, usuario = ?, senha = ?, data_alteracao = now() " +
                    "WHERE id_funcionario = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());

            if (funcionario.getRg() == null) stmt.setNull(3, Types.VARCHAR);
            else stmt.setString(3, funcionario.getRg());

            if (funcionario.getCargo() == null) stmt.setNull(4, Types.VARCHAR);
            else stmt.setString(4, funcionario.getCargo());

            if (funcionario.getSalario() == null) stmt.setNull(5, Types.VARCHAR);
            else stmt.setString(5, funcionario.getSalario());

            stmt.setString(6, funcionario.getUsuario());
            stmt.setString(7, funcionario.getSenha());
            stmt.setInt(8, funcionario.getId());

            int update = stmt.executeUpdate();

            return update > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean excluirFuncionario(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE from funcionarios where id_funcionario = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int delete = stmt.executeUpdate();

            return delete > 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    /*public void insert(Funcionario funcionario) throws SQLException {
        final String sql = "INSERT INTO funcionarios " +
                "(nome, cpf, rg, cargo, salario, usuario, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());

            if (funcionario.getRg() == null) stmt.setNull(3, Types.VARCHAR);
            else stmt.setString(3, funcionario.getRg());

            if (funcionario.getCargo() == null) stmt.setNull(4, Types.VARCHAR);
            else stmt.setString(4, funcionario.getCargo());

            if (funcionario.getSalario() == null) stmt.setNull(5, Types.VARCHAR);
            else stmt.setString(5, funcionario.getSalario());

            stmt.setString(6, funcionario.getUsuario());
            stmt.setString(7, funcionario.getSenha());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    funcionario.setId(rs.getInt(1));
                }
            }
        }
    }*/


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
