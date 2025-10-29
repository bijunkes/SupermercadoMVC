package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class Compras extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField tfProduto;
    private JTextField tfQtde;
    private JTextField tfTotalPagar;
    private JTable table;
    private JButton btAdicionar;
    private JButton btCarrinho;
    private JButton btVoltar;
    private DefaultTableModel modelo;

    public Compras() {
        Color corFundo = new Color(0x25, 0x4D, 0x32);
        Color verdeClaro = new Color(208, 219, 151);

        setBackground(corFundo);
        setPreferredSize(new Dimension(900, 600));
        setLayout(null);

        JLabel lblCatalogo = new JLabel("CATÁLOGO");
		lblCatalogo.setVerticalAlignment(SwingConstants.TOP);
		lblCatalogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatalogo.setForeground(new Color(208, 219, 151));
		lblCatalogo.setFont(new Font("Arial", Font.BOLD, 30));
		lblCatalogo.setBounds(37, 50, 353, 29);
		add(lblCatalogo);
		
		JLabel labelDeProdutos = new JLabel("DE PRODUTOS");
		labelDeProdutos.setVerticalAlignment(SwingConstants.TOP);
		labelDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeProdutos.setForeground(new Color(208, 219, 151));
		labelDeProdutos.setFont(new Font("Arial", Font.BOLD, 30));
		labelDeProdutos.setBounds(37, 80, 353, 29);
		add(labelDeProdutos);
		
		JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setForeground(new Color(37, 77, 50));
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblProdutoTabela.setBackground(new Color(208, 219, 151));
		lblProdutoTabela.setBounds(40, 130, 120, 30);
		add(lblProdutoTabela);
		
		JLabel lblPrecoTabela = new JLabel("PREÇO");
		lblPrecoTabela.setOpaque(true);
		lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoTabela.setForeground(new Color(37, 77, 50));
		lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrecoTabela.setBackground(new Color(208, 219, 151));
		lblPrecoTabela.setBounds(154, 130, 120, 30);
		add(lblPrecoTabela);
		
		JLabel lblQtdeTabela = new JLabel("QTDE");
		lblQtdeTabela.setOpaque(true);
		lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdeTabela.setForeground(new Color(37, 77, 50));
		lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblQtdeTabela.setBackground(new Color(208, 219, 151));
		lblQtdeTabela.setBounds(270, 130, 120, 30);
		add(lblQtdeTabela);

        String[] colunas = {"ID", "Produto", "Preço", "Qtde"};
        modelo = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(modelo);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.BOLD, 18));
        table.setForeground(corFundo);
        table.setBackground(new Color(122, 148, 101));
        table.removeColumn(table.getColumnModel().getColumn(0));

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centralizado);

        table.setBounds(40, 160, 350, 410);
        add(table);

        JLabel lblProduto = new JLabel("PRODUTO");
        lblProduto.setBounds(450, 130, 408, 29);
        lblProduto.setForeground(verdeClaro);
        lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblProduto);

        tfProduto = new JTextField();
        tfProduto.setBounds(450, 160, 408, 50);
        tfProduto.setBorder(new EmptyBorder(10,10,10,10));
        tfProduto.setFont(new Font("Arial", Font.BOLD, 16));
        tfProduto.setBackground(new Color(122, 148, 101));
        tfProduto.setForeground(corFundo);
        add(tfProduto);

        JLabel lblQtde = new JLabel("QUANTIDADE");
        lblQtde.setBounds(450, 220, 408, 29);
        lblQtde.setForeground(verdeClaro);
        lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblQtde);

        tfQtde = new JTextField();
        tfQtde.setBounds(450, 250, 408, 50);
        tfQtde.setBorder(new EmptyBorder(10,10,10,10));
        tfQtde.setFont(new Font("Arial", Font.BOLD, 16));
        tfQtde.setBackground(new Color(122, 148, 101));
        tfQtde.setForeground(corFundo);
        add(tfQtde);

        btAdicionar = new JButton("ADICIONAR");
        btAdicionar.setBounds(678, 320, 180, 50);
        btAdicionar.setBackground(verdeClaro);
        btAdicionar.setFont(new Font("Arial", Font.BOLD, 20));
        add(btAdicionar);

        JLabel lblTotal = new JLabel("TOTAL A PAGAR (R$) / PRODUTO");
        lblTotal.setBounds(450, 440, 408, 29);
        lblTotal.setForeground(verdeClaro);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTotal);

        tfTotalPagar = new JTextField();
        tfTotalPagar.setBounds(450, 470, 408, 50);
        tfTotalPagar.setFont(new Font("Arial", Font.BOLD, 16));
        tfTotalPagar.setBackground(new Color(122, 148, 101));
        tfTotalPagar.setForeground(corFundo);
        add(tfTotalPagar);

        btCarrinho = new JButton(new ImageIcon(getClass().getResource("/icons/carrinho.png")));
        btCarrinho.setBounds(820, 50, 38, 40);
        btCarrinho.setBackground(corFundo);
        add(btCarrinho);

        btVoltar = new JButton(new ImageIcon(getClass().getResource("/icons/voltar.png")));
        btVoltar.setBounds(820, 530, 38, 40);
        btVoltar.setBackground(corFundo);
        btVoltar.setForeground(verdeClaro);
        btVoltar.setOpaque(true);
        btVoltar.setBorderPainted(false);
        add(btVoltar);
    }

    public JTextField getTfProduto() { return tfProduto; }
    public JTextField getTfQtde() { return tfQtde; }
    public JTextField getTfTotalPagar() { return tfTotalPagar; }
    public JTable getTable() { return table; }
    public JButton getBtAdicionar() { return btAdicionar; }
    public JButton getBtCarrinho() { return btCarrinho; }
    public JButton getBtVoltar() { return btVoltar; }
    public DefaultTableModel getModelo() { return modelo; }
}
