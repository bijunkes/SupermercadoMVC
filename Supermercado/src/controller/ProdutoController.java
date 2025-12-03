package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import model.Produto;
import model.ProdutoDAO;
import view.CadastroProdutos;
import view.EditarProduto;

public class ProdutoController {
	private CadastroProdutos view;
	private ProdutoDAO model;
	private Frame frame;

	public ProdutoController(CadastroProdutos view, Frame frame) {
		this.view = view;
		this.model = new ProdutoDAO();
		this.frame = frame;

		this.view.getBtCadastrar().addActionListener(new CadastrarListener());
		this.view.getBtExcluir().addActionListener(new ExcluirListener());
		this.view.getBtEditar().addActionListener(e -> editarProduto());
		this.view.getBtVoltar().addActionListener(e -> this.frame.mostrarInicio());

		atualizarTabela();
	}

	public void atualizarTabela() {
		view.getTableModel().setRowCount(0);
		List<Produto> lista = model.listarProdutos();
		for (Produto p : lista) {
			view.getTableModel().addRow(new Object[] { p.getId(), p.getNome(), p.getPreco(), p.getQtde() });
		}
	}

	private class CadastrarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String nome = view.getNome().trim();
			String precoStr = view.getPreco().trim();
			String qtdeStr = view.getQtde().trim();

			if (nome.isEmpty()) {
				JOptionPane.showMessageDialog(view, "O nome do produto não pode estar vazio.", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (precoStr.isEmpty() || qtdeStr.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Preencha todos os campos antes de cadastrar.", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			double preco;
			int qtde;

			try {
				preco = Double.parseDouble(precoStr);
				qtde = Integer.parseInt(qtdeStr);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(view, "Preço e quantidade devem ser valores numéricos.", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (preco < 0 || qtde < 0) {
				JOptionPane.showMessageDialog(view, "Preço e quantidade não podem ser negativos.", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				Produto p = new Produto(0, nome, preco, qtde);

				if (model.cadastrarProduto(p)) {
					JOptionPane.showMessageDialog(view, "Produto cadastrado com sucesso.", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					view.limparCampos();
					atualizarTabela();
				} else {
					JOptionPane.showMessageDialog(view, "Não foi possível cadastrar o produto.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(view, "Erro durante o cadastro: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class ExcluirListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int row = view.getTable().getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(view, "Selecione um produto para excluir.", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				int id = (int) view.getTableModel().getValueAt(row, 0);

				boolean sucesso = model.excluirProduto(id);

				if (sucesso) {
					JOptionPane.showMessageDialog(view, "Produto excluído com sucesso.", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					atualizarTabela();
				} else {
					JOptionPane.showMessageDialog(view, "Não foi possível excluir o produto.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(view, "Erro ao excluir: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void editarProduto() {

		JTable tabela = view.getTable();
		int linha = tabela.getSelectedRow();

		if (linha == -1) {
			JOptionPane.showMessageDialog(view, "Selecione um produto para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			int id = Integer.parseInt(view.getTableModel().getValueAt(linha, 0).toString());
			String nome = view.getTableModel().getValueAt(linha, 1).toString();
			double preco = Double.parseDouble(view.getTableModel().getValueAt(linha, 2).toString());
			int qtde = Integer.parseInt(view.getTableModel().getValueAt(linha, 3).toString());

			Produto produtoSelecionado = new Produto(id, nome, preco, qtde);

			JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
			EditarProduto telaEditar = new EditarProduto(parentFrame, produtoSelecionado);

			new EditarProdutoController(telaEditar, produtoSelecionado, model);

			telaEditar.setVisible(true);
			atualizarTabela();

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(view, "Erro ao ler os valores do produto selecionado.", "Erro",
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "Erro ao abrir a tela de edição: " + ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public Produto buscarProdutoPorId(int id) {
		return model.buscarPorId(id);
	}

	public Produto buscarProdutoPorNome(String nome) {
		return model.buscarPorNome(nome);
	}

	public boolean removerEstoqueProduto(int idProduto, int quantidade) {
		Produto p = model.buscarPorId(idProduto);
		if (p == null || p.getQtde() < quantidade)
			return false;
		p.setQtde(p.getQtde() - quantidade);
		return model.atualizarProduto(p);
	}

	public boolean adicionarEstoqueProduto(int idProduto, int quantidade) {
		Produto p = model.buscarPorId(idProduto);
		if (p == null)
			return false;
		p.setQtde(p.getQtde() + quantidade);
		return model.atualizarProduto(p);
	}
}
