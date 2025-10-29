package controller;

import javax.swing.JOptionPane;

import model.CarrinhoDAO;
import model.Produto;
import model.ProdutoDAO;
import model.Usuario;
import view.Carrinho;
import view.Compras;

public class ComprasController {
	private Compras view;
    private Frame frame;
    private ProdutoDAO produtoDAO;
    private Carrinho carrinhoView;
    private CarrinhoController carrinhoController;

    public ComprasController(Compras view, Frame frame) {
        this.view = view;
        this.frame = frame;
        this.produtoDAO = new ProdutoDAO();
        this.carrinhoView = new Carrinho();

        this.carrinhoController = new CarrinhoController(
            carrinhoView,
            frame.getUsuarioLogado(),
            frame.getProdutoController(),
            new CarrinhoDAO(), frame
        );

        configurarEventos();
        carregarProdutos();
    }

    private void configurarEventos() {
        view.getTable().getSelectionModel().addListSelectionListener(e -> {
            int linha = view.getTable().getSelectedRow();
            if (linha != -1) {
                String produto = (String) view.getModelo().getValueAt(linha, 1);
                view.getTfProduto().setText(produto);
                view.getTfQtde().setText("1");
                atualizarTotal();
            }
        });

        view.getTfQtde().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                atualizarTotal();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                atualizarTotal();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                atualizarTotal();
            }
        });

        view.getBtAdicionar().addActionListener(e -> adicionarProduto());
        view.getBtCarrinho().addActionListener(e -> frame.mostrarCarrinho());
        view.getBtVoltar().addActionListener(e -> frame.mostrarInicio());
    }

    private void carregarProdutos() {
        var modelo = view.getModelo();
        modelo.setRowCount(0);

        for (Produto p : produtoDAO.listarProdutos()) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getQtde()
            });
        }
    }

    private void adicionarProduto() {
        int linha = view.getTable().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um produto.");
            return;
        }

        try {
            int idProduto = (int) view.getModelo().getValueAt(linha, 0);
            double preco = Double.parseDouble(view.getModelo().getValueAt(linha, 2).toString());
            int qtde = Integer.parseInt(view.getTfQtde().getText());
            Usuario usuario = frame.getUsuarioLogado();

            if (usuario == null) {
                JOptionPane.showMessageDialog(view, "Nenhum usuário logado.");
                return;
            }

            boolean sucesso = carrinhoController.adicionarProduto(usuario.getId(), idProduto, qtde, preco);
            if (sucesso) {
                JOptionPane.showMessageDialog(view, "Produto adicionado ao carrinho.");
                
                atualizarTabela();
                
                double totalCompra = carrinhoController.calcularTotalCompra();
                view.getTfTotalPagar().setText(String.format("%.2f", totalCompra));

                // LIMPAR CAMPOS
                view.getTfProduto().setText("");
                view.getTfQtde().setText("");
                view.getTfTotalPagar().setText("");
                view.getTable().clearSelection();
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao adicionar produto.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Quantidade inválida.");
        }
    }

    private void atualizarTabela() {
        var modelo = view.getModelo();
        modelo.setRowCount(0);

        for (Produto p : produtoDAO.listarProdutos()) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getQtde()
            });
        }
    }

    private void atualizarTotal() {
        int linha = view.getTable().getSelectedRow();
        if (linha != -1) {
            try {
                double preco = Double.parseDouble(view.getModelo().getValueAt(linha, 2).toString());
                int qtde = Integer.parseInt(view.getTfQtde().getText());
                view.getTfTotalPagar().setText(String.format("%.2f", preco * qtde));
            } catch (Exception e) {
                view.getTfTotalPagar().setText("0.00");
            }
        }
    }
}
