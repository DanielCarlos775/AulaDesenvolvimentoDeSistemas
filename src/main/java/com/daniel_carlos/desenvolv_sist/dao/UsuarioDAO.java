package com.daniel_carlos.desenvolv_sist.dao;

import com.daniel_carlos.desenvolv_sist.model.Usuario;
import com.daniel_carlos.desenvolv_sist.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // login falhou
    }
}
