package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.CarrinhoDAO;
import model.Produto;
import model.Usuario;
import view.Carrinho;

public class CarrinhoController {

    private Carrinho view;
    private Usuario usuarioLogado;
    private ProdutoController produtoController;
    private CarrinhoDAO carrinhoDAO;

    public CarrinhoController(Carrinho view, Usuario usuarioLogado, ProdutoController produtoController, CarrinhoDAO carrinhoDAO, Frame frame) {
        this.view = view;
        this.usuarioLogado = usuarioLogado;
        this.produtoController = produtoController;
        this.carrinhoDAO = carrinhoDAO;

        this.view.setCarrinhoListener(new Carrinho.CarrinhoListener() {
            @Override
            public void onAtualizar(int linhaSelecionada, int novaQtde) {
                atualizarQuantidade(linhaSelecionada, novaQtde);
            }

            @Override
            public void onNotaFiscal() {
                gerarNotaFiscal();
            }

            @Override
            public void onVoltar() {
                frame.mostrarCompras();
            }

            @Override
            public void onLinhaSelecionada(int linha) {
                atualizarCamposLinhaSelecionada(linha);
            }
        });

        carregarCarrinho();
    }
    
    public Object[][] getProdutosCarrinho() {
        List<model.Carrinho> produtos = carrinhoDAO.listarProdutos(usuarioLogado.getId());
        Map<Integer, Object[]> mapa = new HashMap<>();

        for (model.Carrinho c : produtos) {
            if (mapa.containsKey(c.getIdProduto())) {
                Object[] item = mapa.get(c.getIdProduto());
                int qtdeAtual = (int) item[2];
                item[2] = qtdeAtual + c.getQtde();
            } else {
                Produto p = produtoController.buscarProdutoPorId(c.getIdProduto());
                mapa.put(c.getIdProduto(), new Object[]{p.getNome(), p.getPreco(), c.getQtde()});
            }
        }

        return mapa.values().toArray(new Object[0][]);
    }


    private void carregarCarrinho() {
        List<model.Carrinho> produtos = carrinhoDAO.listarProdutos(usuarioLogado.getId());
        Map<Integer, Object[]> mapa = new HashMap<>();

        for (model.Carrinho c : produtos) {
            if (mapa.containsKey(c.getIdProduto())) {
                Object[] item = mapa.get(c.getIdProduto());
                int qtdeAtual = (int) item[2];
                item[2] = qtdeAtual + c.getQtde();
            } else {
                Produto p = produtoController.buscarProdutoPorId(c.getIdProduto());
                mapa.put(c.getIdProduto(), new Object[]{p.getNome(), c.getPreco(), c.getQtde()});
            }
        }

        Object[][] dados = mapa.values().toArray(new Object[0][]);
        view.setTabela(dados);

        if (dados.length > 0) {
            atualizarCamposLinhaSelecionada(0);
        }
    }

    private void atualizarCamposLinhaSelecionada(int linha) {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabela().getModel();
        if (linha < 0 || linha >= modelo.getRowCount()) return;

        String nome = modelo.getValueAt(linha, 0).toString();
        int qtde = Integer.parseInt(modelo.getValueAt(linha, 2).toString());
        double preco = Double.parseDouble(modelo.getValueAt(linha, 1).toString());
        double totalItem = preco * qtde;

        double totalCompra = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int q = Integer.parseInt(modelo.getValueAt(i, 2).toString());
            double p = Double.parseDouble(modelo.getValueAt(i, 1).toString());
            totalCompra += q * p;
        }

        view.setProdutoSelecionado(nome, qtde, totalCompra);
    }

    public void atualizarTabelaCarrinho() {
        carregarCarrinho();
    }
    
    public double calcularTotalCompra() {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabela().getModel();
        double total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            double preco = Double.parseDouble(modelo.getValueAt(i, 1).toString());
            int qtde = Integer.parseInt(modelo.getValueAt(i, 2).toString());
            total += preco * qtde;
        }
        return total;
    }

    
    private void atualizarQuantidade(int linha, int novaQtde) {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabela().getModel();
        String nomeProduto = modelo.getValueAt(linha, 0).toString();
        Produto p = produtoController.buscarProdutoPorNome(nomeProduto);

        int qtdeAtual = Integer.parseInt(modelo.getValueAt(linha, 2).toString());
        int diferenca = novaQtde - qtdeAtual;

        boolean sucesso = false;
        if (diferenca > 0) {
            sucesso = carrinhoDAO.adicionarQuantidade(usuarioLogado.getId(), p.getId(), diferenca)
                    && produtoController.removerEstoqueProduto(p.getId(), diferenca);
        } else if (diferenca < 0) {
            sucesso = carrinhoDAO.diminuirQuantidade(usuarioLogado.getId(), p.getId(), Math.abs(diferenca))
                    && produtoController.adicionarEstoqueProduto(p.getId(), Math.abs(diferenca));
        } else {
            sucesso = true;
        }

        if (!sucesso) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o carrinho.");
            return;
        }

        carregarCarrinho();
        JOptionPane.showMessageDialog(null, "Quantidade atualizada!");
    }

    private void gerarNotaFiscal() {
        List<model.Carrinho> produtos = carrinhoDAO.listarProdutos(usuarioLogado.getId());
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Carrinho vazio!");
            return;
        }

        for (model.Carrinho c : produtos) {
            Produto p = produtoController.buscarProdutoPorId(c.getIdProduto());
            c.setProduto(p);
        }

        NotaFiscalController nfController = new NotaFiscalController((JFrame) SwingUtilities.getWindowAncestor(view));
        nfController.emitirNota(usuarioLogado, produtos);

        for (model.Carrinho c : produtos) {
            carrinhoDAO.removerProduto(c.getId());
        }

        carregarCarrinho();
    }


    public boolean adicionarProduto(int idUsuario, int idProduto, int qtde, double preco) {
        model.Carrinho c = new model.Carrinho(qtde, qtde, qtde, qtde, preco);
        c.setIdUsuario(idUsuario);
        c.setIdProduto(idProduto);
        c.setQtde(qtde);
        c.setPreco(preco);

        return carrinhoDAO.adicionarProduto(c);
    }
      
}
