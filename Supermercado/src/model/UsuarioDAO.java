package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.BancoDeDados;

public class UsuarioDAO {

	public boolean cadastrarUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuarios (nome, cpf, admin) VALUES (?, ?, ?)";

		try (Connection conexao = BancoDeDados.conectar();
				PreparedStatement pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getCpf());
			pstm.setBoolean(3, usuario.isAdmin());

			int linhas = pstm.executeUpdate();
			if (linhas == 0) {
				return false;
			}

			try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					usuario.setId(generatedKeys.getInt(1));
				}
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Usuario logarUsuario(String nome, String cpf) {
		String sql = "SELECT * FROM usuarios WHERE nome = ? AND cpf = ?";

		try (Connection conexao = BancoDeDados.conectar(); PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setString(1, nome);
			pstm.setString(2, cpf);

			try (ResultSet rs = pstm.executeQuery()) {
				if (rs.next()) {
					return new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"),
							rs.getBoolean("admin"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
