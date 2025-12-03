package controller;

import model.Produto;
import model.ProdutoDAO;
import view.EditarProduto;

public class EditarProdutoController {
	private EditarProduto view;
	private Produto produto;
	private ProdutoDAO produtoDAO;

	public EditarProdutoController(EditarProduto view, Produto produto, ProdutoDAO produtoDAO) {
		this.view = view;
		this.produto = produto;
		this.produtoDAO = produtoDAO;

		this.view.adicionarAcaoSalvar(e -> salvarProduto());
	}

	private void salvarProduto() {
		try {

			String nome = view.getNome().trim();
			String precoStr = view.getPreco().trim();
			String qtdeStr = view.getQtde().trim();

			if (nome.isEmpty()) {
				view.mostrarMensagem("O nome do produto não pode estar vazio.");
				return;
			}

			if (precoStr.isEmpty() || qtdeStr.isEmpty()) {
				view.mostrarMensagem("Preencha todos os campos antes de salvar.");
				return;
			}

			double preco;
			int qtde;

			try {
				preco = Double.parseDouble(precoStr);
				qtde = Integer.parseInt(qtdeStr);
			} catch (NumberFormatException ex) {
				view.mostrarMensagem("Preço e quantidade devem ser valores numéricos.");
				return;
			}

			if (preco < 0 || qtde < 0) {
				view.mostrarMensagem("Preço e quantidade não podem ser negativos.");
				return;
			}

			produto.setNome(nome);
			produto.setPreco(preco);
			produto.setQtde(qtde);

			if (produtoDAO.atualizarProduto(produto)) {
				view.mostrarMensagem("Produto atualizado com sucesso!");
				view.dispose();
			} else {
				view.mostrarMensagem("Erro ao atualizar produto.");
			}

		} catch (Exception ex) {
			view.mostrarMensagem("Erro inesperado ao salvar: " + ex.getMessage());
		}
	}
}
