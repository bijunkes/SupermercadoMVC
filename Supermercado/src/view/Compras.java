package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setLayout(new MigLayout("fill, insets 20", "50[180][180][180][][][][][][]",
				"[40][40][40][50][][][][50][][][][][][50][]"));

		JLabel lblCatalogo = new JLabel("CATÁLOGO");
		lblCatalogo.setVerticalAlignment(SwingConstants.CENTER);
		lblCatalogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatalogo.setForeground(new Color(208, 219, 151));
		lblCatalogo.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblCatalogo, "cell 0 0 3 1,alignx center,aligny bottom");

		String[] colunas = { "ID", "Produto", "Preço", "Qtde" };
		modelo = new DefaultTableModel(colunas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		table = new JTable(modelo);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setForeground(corFundo);
		table.setBackground(new Color(122, 148, 101));
		table.removeColumn(table.getColumnModel().getColumn(0));
		table.setDefaultRenderer(Object.class, centralizado);

		btCarrinho = new JButton(new ImageIcon(getClass().getResource("/icons/carrinho.png")));
		btCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btCarrinho.setBackground(corFundo);
		add(btCarrinho, "cell 8 0,alignx right,aligny top");

		JLabel labelDeProdutos = new JLabel("DE PRODUTOS");
		labelDeProdutos.setVerticalAlignment(SwingConstants.TOP);
		labelDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeProdutos.setForeground(new Color(208, 219, 151));
		labelDeProdutos.setFont(new Font("Arial", Font.BOLD, 30));
		add(labelDeProdutos, "cell 0 1 3 1,alignx center,aligny top");

		JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setForeground(new Color(37, 77, 50));
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblProdutoTabela.setBackground(new Color(208, 219, 151));
		add(lblProdutoTabela, "cell 0 2,alignx center,aligny top, grow");

		JLabel lblPrecoTabela = new JLabel("PREÇO");
		lblPrecoTabela.setOpaque(true);
		lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoTabela.setForeground(new Color(37, 77, 50));
		lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrecoTabela.setBackground(new Color(208, 219, 151));
		add(lblPrecoTabela, "cell 1 2,alignx center,aligny top, grow");

		JLabel lblQtdeTabela = new JLabel("QTDE");
		lblQtdeTabela.setOpaque(true);
		lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdeTabela.setForeground(new Color(37, 77, 50));
		lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblQtdeTabela.setBackground(new Color(208, 219, 151));
		add(lblQtdeTabela, "cell 2 2,alignx center,aligny top, grow");

		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProduto.setForeground(verdeClaro);
		lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblProduto, "cell 6 2,aligny bottom,grow");

		add(table, "cell 0 3 3 11,grow");

		tfProduto = new JTextField();
		tfProduto.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfProduto.setFont(new Font("Arial", Font.BOLD, 16));
		tfProduto.setBackground(new Color(122, 148, 101));
		tfProduto.setForeground(corFundo);
		add(tfProduto, "cell 6 3 2 1,aligny top,grow");

		JLabel lblQtde = new JLabel("QUANTIDADE");
		lblQtde.setVerticalAlignment(SwingConstants.BOTTOM);
		lblQtde.setForeground(verdeClaro);
		lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblQtde, "cell 6 5,growx,aligny bottom");

		tfQtde = new JTextField();
		tfQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfQtde.setFont(new Font("Arial", Font.BOLD, 16));
		tfQtde.setBackground(new Color(122, 148, 101));
		tfQtde.setForeground(corFundo);
		add(tfQtde, "cell 6 6 2 1,aligny bottom,grow");

		btAdicionar = new JButton("ADICIONAR");
		btAdicionar.setForeground(corFundo);
		btAdicionar.setBackground(verdeClaro);
		btAdicionar.setFont(new Font("Arial", Font.BOLD, 20));
		add(btAdicionar, "cell 6 8 2 1,alignx right,aligny bottom");

		JLabel lblTotal = new JLabel("TOTAL A PAGAR (R$) / PRODUTO");
		lblTotal.setForeground(verdeClaro);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblTotal, "cell 6 11,alignx left,aligny bottom");

		tfTotalPagar = new JTextField();
		tfTotalPagar.setFont(new Font("Arial", Font.BOLD, 16));
		tfTotalPagar.setBackground(new Color(122, 148, 101));
		tfTotalPagar.setForeground(corFundo);
		add(tfTotalPagar, "cell 6 13 2 1,aligny top,grow");

		btVoltar = new JButton(new ImageIcon(getClass().getResource("/icons/voltar.png")));
		btVoltar.setBackground(corFundo);
		btVoltar.setForeground(verdeClaro);
		btVoltar.setOpaque(true);
		btVoltar.setBorderPainted(false);
		add(btVoltar, "cell 8 14,alignx left,aligny top");
	}

	public JTextField getTfProduto() {
		return tfProduto;
	}

	public JTextField getTfQtde() {
		return tfQtde;
	}

	public JTextField getTfTotalPagar() {
		return tfTotalPagar;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtAdicionar() {
		return btAdicionar;
	}

	public JButton getBtCarrinho() {
		return btCarrinho;
	}

	public JButton getBtVoltar() {
		return btVoltar;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}
}
