package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.BancoDeDados;

public class CarrinhoDAO {

	public boolean adicionarProduto(Carrinho carrinho) {
		String sql = "INSERT INTO carrinho (id_usuario, id_produto, qtde, preco) VALUES (?, ?, ?, ?)";
		try (Connection conexao = BancoDeDados.conectar();
				PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setInt(1, carrinho.getIdUsuario());
			stmt.setInt(2, carrinho.getIdProduto());
			stmt.setInt(3, carrinho.getQtde());
			stmt.setDouble(4, carrinho.getPreco());

			int linhas = stmt.executeUpdate();

			if (linhas > 0) {
				String sqlUpdate = "UPDATE produtos SET qtde = qtde - ? WHERE id = ? AND qtde >= ?";
				try (PreparedStatement stmtUpdate = conexao.prepareStatement(sqlUpdate)) {
					stmtUpdate.setInt(1, carrinho.getQtde());
					stmtUpdate.setInt(2, carrinho.getIdProduto());
					stmtUpdate.setInt(3, carrinho.getQtde());

					int atualizado = stmtUpdate.executeUpdate();
					if (atualizado > 0) {
						return true;
					} else {
						String sqlDelete = "DELETE FROM carrinho WHERE id_produto = ? AND id_usuario = ? LIMIT 1";
						try (PreparedStatement stmtDelete = conexao.prepareStatement(sqlDelete)) {
							stmtDelete.setInt(1, carrinho.getIdProduto());
							stmtDelete.setInt(2, carrinho.getIdUsuario());
							stmtDelete.executeUpdate();
						}
						return false;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean atualizarQuantidade(int id, int qtde) {
		String sql = "UPDATE carrinho SET qtde = ? WHERE id = ?";
		try (Connection conexao = BancoDeDados.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, qtde);
			stmt.setInt(2, id);

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removerProduto(int id) {
		String sql = "DELETE FROM carrinho WHERE id = ?";
		try (Connection conexao = BancoDeDados.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Carrinho> listarProdutos(int idUsuario) {
		List<Carrinho> lista = new ArrayList<>();
		String sql = "SELECT * FROM carrinho WHERE id_usuario = ?";
		try (Connection conexao = BancoDeDados.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, idUsuario);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Carrinho c = new Carrinho(rs.getInt("id"), rs.getInt("id_usuario"), rs.getInt("id_produto"),
							rs.getInt("qtde"), rs.getDouble("preco"));
					lista.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean adicionarQuantidade(int idUsuario, int idProduto, int qtde) {
		List<Carrinho> produtos = listarProdutos(idUsuario);
		for (Carrinho c : produtos) {
			if (c.getIdProduto() == idProduto) {
				return atualizarQuantidade(c.getId(), c.getQtde() + qtde);
			}
		}
		return false;
	}

	public boolean diminuirQuantidade(int idUsuario, int idProduto, int qtde) {
		List<Carrinho> produtos = listarProdutos(idUsuario);
		for (Carrinho c : produtos) {
			if (c.getIdProduto() == idProduto) {
				int novaQtde = c.getQtde() - qtde;
				if (novaQtde > 0) {
					return atualizarQuantidade(c.getId(), novaQtde);
				} else {
					return removerProduto(c.getId());
				}
			}
		}
		return false;
	}

}