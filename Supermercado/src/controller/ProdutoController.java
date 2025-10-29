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
            view.getTableModel().addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQtde()});
        }
    }

    private class CadastrarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nome = view.getNome();
            double preco;
            int qtde;

            try {
                preco = Double.parseDouble(view.getPreco());
                qtde = Integer.parseInt(view.getQtde());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Preço e quantidade devem ser numéricos", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Produto p = new Produto(0, nome, preco, qtde);
            if (model.cadastrarProduto(p)) {
                JOptionPane.showMessageDialog(view, "Produto cadastrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                view.limparCampos();
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(view, "Não foi possível cadastrar", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ExcluirListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(view, "Selecione um produto", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = (int) view.getTableModel().getValueAt(row, 0);
            if (model.excluirProduto(id)) {
                JOptionPane.showMessageDialog(view, "Produto excluído", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(view, "Não foi possível excluir", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarProduto() {
        JTable tabela = view.getTable();
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um produto para editar!");
            return;
        }

        int id = (int) view.getTableModel().getValueAt(linha, 0);
        String nome = (String) view.getTableModel().getValueAt(linha, 1);
        double preco = (double) view.getTableModel().getValueAt(linha, 2);
        int qtde = (int) view.getTableModel().getValueAt(linha, 3);

        Produto produtoSelecionado = new Produto(id, nome, preco, qtde);

        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
        EditarProduto telaEditar = new EditarProduto(parentFrame, produtoSelecionado);
        EditarProdutoController controllerEditar = new EditarProdutoController(telaEditar, produtoSelecionado, model);

        telaEditar.setVisible(true);
        atualizarTabela();
    }
    
    public Produto buscarProdutoPorId(int id) {
        return model.buscarPorId(id);
    }

    public Produto buscarProdutoPorNome(String nome) {
        return model.buscarPorNome(nome);
    }

    public boolean removerEstoqueProduto(int idProduto, int quantidade) {
        Produto p = model.buscarPorId(idProduto);
        if (p == null || p.getQtde() < quantidade) return false;
        p.setQtde(p.getQtde() - quantidade);
        return model.atualizarProduto(p);
    }

    public boolean adicionarEstoqueProduto(int idProduto, int quantidade) {
        Produto p = model.buscarPorId(idProduto);
        if (p == null) return false;
        p.setQtde(p.getQtde() + quantidade);
        return model.atualizarProduto(p);
    }
}
