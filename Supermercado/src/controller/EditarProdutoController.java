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
            String nome = view.getNome();
            double preco = Double.parseDouble(view.getPreco());
            int qtde = Integer.parseInt(view.getQtde());

            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setQtde(qtde);

            if (produtoDAO.atualizarProduto(produto)) {
                view.mostrarMensagem("Produto atualizado com sucesso!");
                view.dispose();
            } else {
                view.mostrarMensagem("Erro ao atualizar produto.");
            }
        } catch (NumberFormatException ex) {
            view.mostrarMensagem("Preencha os campos corretamente!");
        }
    }
}
