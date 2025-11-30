package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Produto;
import net.miginfocom.swing.MigLayout;

public class Carrinho extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField tfProduto;
    private JTextField tfQtde;
    private JTextField tfTotal;
    private JTable table;
    private JButton btnAtualizar;
    private JButton btnNotaFiscal;
    private JButton btnVoltar;

    public interface CarrinhoListener {
        void onAtualizar(int linhaSelecionada, int novaQtde);
        void onNotaFiscal();
        void onVoltar();
        void onLinhaSelecionada(int linha);
    }

    private CarrinhoListener listener;
    public void setCarrinhoListener(CarrinhoListener listener) {
        this.listener = listener;
    }

    public Carrinho() {
        Color corFundo = new Color(0x25, 0x4D, 0x32);
        Color verdeClaro = new Color(208, 219, 151);
        Color verdeClaroTransparente = new Color(122, 148, 101);

        setBackground(corFundo);
        setPreferredSize(new Dimension(900, 600));
        setLayout(new MigLayout(
        	    "fill",
        	    "50[180][180][180]30[][][][]30",
        	    "30[40][40][40][50][][][][50][][][][][][50][]50"
        	));


        JLabel lblCarrinho = new JLabel("CARRINHO");
		lblCarrinho.setVerticalAlignment(SwingConstants.TOP);
		lblCarrinho.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarrinho.setForeground(new Color(208, 219, 151));
		lblCarrinho.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblCarrinho, "cell 0 0 3 1,grow");
		
		JLabel labelDeProdutos = new JLabel("DE PRODUTOS");
		labelDeProdutos.setVerticalAlignment(SwingConstants.TOP);
		labelDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeProdutos.setForeground(new Color(208, 219, 151));
		labelDeProdutos.setFont(new Font("Arial", Font.BOLD, 30));
		add(labelDeProdutos, "cell 0 1 3 1,grow");
		
		JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setForeground(new Color(37, 77, 50));
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblProdutoTabela.setBackground(new Color(208, 219, 151));
		add(lblProdutoTabela, "cell 0 2,grow");
		
		JLabel lblPrecoTabela = new JLabel("PREÇO");
		lblPrecoTabela.setOpaque(true);
		lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoTabela.setForeground(new Color(37, 77, 50));
		lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrecoTabela.setBackground(new Color(208, 219, 151));
		add(lblPrecoTabela, "cell 1 2,alignx center,grow");
		
		JLabel lblQtdeTabela = new JLabel("QTDE");
		lblQtdeTabela.setOpaque(true);
		lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdeTabela.setForeground(new Color(37, 77, 50));
		lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblQtdeTabela.setBackground(new Color(208, 219, 151));
		add(lblQtdeTabela, "cell 2 2,grow");

		String[] colunas = {"ID", "Produto", "Preço", "Qtde"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(modelo);
        table.removeColumn(table.getColumnModel().getColumn(3));
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.BOLD, 18));
        table.setForeground(corFundo);
        table.setBackground(verdeClaroTransparente);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centralizado);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && listener != null) {
                listener.onLinhaSelecionada(table.getSelectedRow());
            }
        });
        add(table, "cell 0 3 3 13,grow");

        JLabel lblInfo = new JLabel("INFORMAÇÕES");
        lblInfo.setForeground(verdeClaro);
        lblInfo.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblInfo, "cell 3 1 3 1,grow");

        JLabel lblProduto = new JLabel("PRODUTO");
        lblProduto.setForeground(verdeClaro);
        lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblProduto, "cell 3 2 3 1,grow");

        tfProduto = new JTextField();
        tfProduto.setEditable(false);
        tfProduto.setBackground(verdeClaroTransparente);
        tfProduto.setForeground(corFundo);
        tfProduto.setFont(new Font("Arial", Font.BOLD, 16));
        tfProduto.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(tfProduto, "cell 3 3 3 1,grow");

        JLabel lblQtde = new JLabel("QUANTIDADE");
        lblQtde.setForeground(verdeClaro);
        lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblQtde, "cell 3 5 3 1,grow");

        tfQtde = new JTextField();
        tfQtde.setBackground(verdeClaroTransparente);
        tfQtde.setForeground(corFundo);
        tfQtde.setFont(new Font("Arial", Font.BOLD, 16));
        tfQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(tfQtde, "cell 3 7 3 1,grow");

        JLabel lblTotal = new JLabel("TOTAL A PAGAR (R$)");
        lblTotal.setForeground(verdeClaro);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTotal, "cell 3 11 3 1,grow");

        tfTotal = new JTextField();
        tfTotal.setEditable(false);
        tfTotal.setBackground(verdeClaroTransparente);
        tfTotal.setForeground(corFundo);
        tfTotal.setFont(new Font("Arial", Font.BOLD, 16));
        tfTotal.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(tfTotal, "cell 3 13 3 1,grow");

        btnAtualizar = new JButton("SALVAR");
        btnAtualizar.setOpaque(true);
        btnAtualizar.setForeground(new Color(37, 77, 50));
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 20));
        btnAtualizar.setBorderPainted(false);
        btnAtualizar.setBackground(new Color(208, 219, 151));
        add(btnAtualizar, "cell 5 9,grow");

        btnNotaFiscal = new JButton("NOTA FISCAL");
        btnNotaFiscal.setOpaque(true);
        btnNotaFiscal.setForeground(new Color(37, 77, 50));
        btnNotaFiscal.setFont(new Font("Arial", Font.BOLD, 20));
        btnNotaFiscal.setBorderPainted(false);
        btnNotaFiscal.setBackground(new Color(208, 219, 151));
        add(btnNotaFiscal, "cell 3 15,grow");

        btnVoltar = new JButton(new ImageIcon(getClass().getResource("/icons/voltar.png")));
        btnVoltar.setBackground(corFundo);
        btnVoltar.setBorderPainted(false);
        add(btnVoltar, "cell 5 15,alignx right,aligny bottom");
        
        btnAtualizar.addActionListener(e -> {
            if (listener != null) {
                int linha = table.getSelectedRow();
                if (linha >= 0) {
                    try {
                        int novaQtde = Integer.parseInt(tfQtde.getText());
                        listener.onAtualizar(linha, novaQtde);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Quantidade inválida!");
                    }
                }
            }
        });

        btnNotaFiscal.addActionListener(e -> {
            if (listener != null) listener.onNotaFiscal();
        });

        btnVoltar.addActionListener(e -> {
            if (listener != null) listener.onVoltar();
        });

    }

    public void setProdutoSelecionado(String nome, int qtde, double total) {
        tfProduto.setText(nome);
        tfQtde.setText(String.valueOf(qtde));
        tfTotal.setText(String.format("%.2f", total));
    }

    public void setTabela(Object[][] dados) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        for(Object[] linha : dados) {
            modelo.addRow(linha);
        }
    }

    public JTable getTabela() {
        return table;
    }

    public JButton getBtnAtualizar() { return btnAtualizar; }
    public JButton getBtnNotaFiscal() { return btnNotaFiscal; }
    public JButton getBtnVoltar() { return btnVoltar; }
    public JTextField getTfProduto() { return tfProduto; }
    public JTextField getTfQtde() { return tfQtde; }
    public JTextField getTfTotal() { return tfTotal; }

	public void setProduto(Produto p) {
		// TODO Auto-generated method stub
		
	}
    
    
}
