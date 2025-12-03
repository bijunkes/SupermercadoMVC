package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.BancoDeDados;

public class ProdutoDAO {

	public boolean cadastrarProduto(Produto produto) {
		String sql = "INSERT INTO produtos (nome, preco, qtde) VALUES (?, ?, ?)";
		try (Connection conn = BancoDeDados.conectar();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setInt(3, produto.getQtde());

			int linhas = stmt.executeUpdate();
			if (linhas > 0) {
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next())
						produto.setId(rs.getInt(1));
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean excluirProduto(int id) {
		String sql = "DELETE FROM produtos WHERE id = ?";
		try (Connection conn = BancoDeDados.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Produto> listarProdutos() {
		List<Produto> lista = new ArrayList<>();
		String sql = "SELECT * FROM produtos";
		try (Connection conn = BancoDeDados.conectar();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				lista.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getInt("qtde")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean atualizarProduto(Produto produto) {
		String sql = "UPDATE produtos SET nome = ?, preco = ?, qtde = ? WHERE id = ?";

		try (Connection conn = BancoDeDados.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setInt(3, produto.getQtde());
			stmt.setInt(4, produto.getId());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Produto buscarPorId(int id) {
		String sql = "SELECT * FROM produtos WHERE id = ?";
		try (Connection conn = BancoDeDados.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getInt("qtde"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Produto buscarPorNome(String nome) {
		String sql = "SELECT * FROM produtos WHERE nome = ?";
		try (Connection conn = BancoDeDados.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, nome);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getInt("qtde"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
