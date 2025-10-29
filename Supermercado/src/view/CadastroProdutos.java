package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class CadastroProdutos extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField tfNome, tfPreco, tfQtde;
    private JButton btCadastrar, btEditar, btExcluir, btVoltar;
    private JTable table;
    private DefaultTableModel tableModel;

    public CadastroProdutos() {
    	Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(null);

		JLabel labelCadastro = new JLabel("CADASTRO");
		labelCadastro.setVerticalAlignment(SwingConstants.TOP);
		labelCadastro.setBounds(95, 85, 300, 29);
		labelCadastro.setForeground(verdeClaro);
		labelCadastro.setFont(new Font("Arial", Font.BOLD, 30));
		labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelCadastro);
		
		JLabel labelDeProdutos = new JLabel("DE PRODUTOS");
		labelDeProdutos.setVerticalAlignment(SwingConstants.TOP);
		labelDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeProdutos.setForeground(new Color(208, 219, 151));
		labelDeProdutos.setFont(new Font("Arial", Font.BOLD, 30));
		labelDeProdutos.setBounds(95, 113, 300, 29);
		add(labelDeProdutos);
		
		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduto.setForeground(new Color(208, 219, 151));
		lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
		lblProduto.setBounds(95, 170, 220, 29);
		add(lblProduto);

		tfNome = new JTextField();
		tfNome.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfNome.setToolTipText("PRODUTO");
		tfNome.setForeground(corFundo);
		tfNome.setFont(new Font("Arial", Font.BOLD, 16));
		tfNome.setBackground(verdeClaroTransparente);
		tfNome.setBounds(95, 200, 300, 50);
		add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblPreco = new JLabel("PREÇO (R$)");
		lblPreco.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPreco.setHorizontalAlignment(SwingConstants.LEFT);
		lblPreco.setForeground(new Color(208, 219, 151));
		lblPreco.setFont(new Font("Arial", Font.BOLD, 16));
		lblPreco.setBounds(95, 255, 220, 29);
		add(lblPreco);
		
		tfPreco = new JTextField();
		tfPreco.setToolTipText("PREÇO");
		tfPreco.setForeground(corFundo);
		tfPreco.setFont(new Font("Arial", Font.BOLD, 16));
		tfPreco.setColumns(10);
		tfPreco.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfPreco.setBackground(new Color(122, 148, 101));
		tfPreco.setBounds(95, 285, 300, 50);
		add(tfPreco);

		JLabel lblQtde = new JLabel("QUANTIDADE");
		lblQtde.setVerticalAlignment(SwingConstants.BOTTOM);
		lblQtde.setHorizontalAlignment(SwingConstants.LEFT);
		lblQtde.setForeground(new Color(208, 219, 151));
		lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
		lblQtde.setBounds(95, 345, 220, 29);
		add(lblQtde);
		
		tfQtde = new JTextField();
		tfQtde.setToolTipText("QUANTIDADE");
		tfQtde.setForeground(corFundo);
		tfQtde.setFont(new Font("Arial", Font.BOLD, 16));
		tfQtde.setColumns(10);
		tfQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfQtde.setBackground(new Color(122, 148, 101));
		tfQtde.setBounds(95, 375, 300, 50);
		add(tfQtde);

        btCadastrar = new JButton("CADASTRAR");
        btCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
        btCadastrar.setBounds(145, 465, 200, 50);
        btCadastrar.setBackground(verdeClaro);
        btCadastrar.setForeground(corFundo); 
        btCadastrar.setOpaque(true);
        btCadastrar.setBorderPainted(false);
        add(btCadastrar);
        
        JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setForeground(corFundo);
		lblProdutoTabela.setBackground(verdeClaro);
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblProdutoTabela.setBounds(500, 55, 120, 30);
		add(lblProdutoTabela);
		
		JLabel lblPrecoTabela = new JLabel("PREÇO");
		lblPrecoTabela.setOpaque(true);
		lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoTabela.setForeground(new Color(37, 77, 50));
		lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrecoTabela.setBackground(new Color(208, 219, 151));
		lblPrecoTabela.setBounds(614, 55, 120, 30);
		add(lblPrecoTabela);
		
		JLabel lblQtdeTabela = new JLabel("QTDE");
		lblQtdeTabela.setOpaque(true);
		lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdeTabela.setForeground(new Color(37, 77, 50));
		lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblQtdeTabela.setBackground(new Color(208, 219, 151));
		lblQtdeTabela.setBounds(730, 55, 120, 30);
		add(lblQtdeTabela);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setForeground(corFundo);
		table.setBackground(verdeClaroTransparente);
		table.setRowHeight(30);
		table.setBounds(500, 85, 350, 430);
		
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centralizado);
		
		String[] colunas = {"ID", "PRODUTO", "PREÇO", "QTDE"};
		tableModel = new DefaultTableModel(colunas, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		table.setModel(tableModel);
		table.removeColumn(table.getColumnModel().getColumn(0));
		add(table);

        btEditar = new JButton("EDITAR");
        btEditar.setBounds(500, 530, 115, 40);
        btEditar.setBackground(new Color(208, 219, 151));
        btEditar.setForeground(new Color(37, 77, 50));
        btEditar.setOpaque(true);
        btEditar.setBorderPainted(false);
        btEditar.setFont(new Font("Arial", Font.BOLD, 18));
        add(btEditar);

        btExcluir = new JButton("EXCLUIR");
        btExcluir.setBounds(630, 530, 115, 40);
        btExcluir.setBackground(verdeClaroTransparente);
        btExcluir.setForeground(new Color(37, 77, 50));
        btExcluir.setOpaque(true);
        btExcluir.setBorderPainted(false);
        btExcluir.setFont(new Font("Arial", Font.BOLD, 18));
        add(btExcluir);

        btVoltar = new JButton(new ImageIcon(getClass().getResource("/icons/voltar.png")));
        btVoltar.setBounds(820, 530, 38, 40);
        btVoltar.setBackground(corFundo);
        btVoltar.setForeground(verdeClaro);
        btVoltar.setOpaque(true);
        btVoltar.setBorderPainted(false);
        add(btVoltar);
    }

    public String getNome() { return tfNome.getText(); }
    public String getPreco() { return tfPreco.getText(); }
    public String getQtde() { return tfQtde.getText(); }

    public JButton getBtCadastrar() { return btCadastrar; }
    public JButton getBtEditar() { return btEditar; }
    public JButton getBtExcluir() { return btExcluir; }
    public JButton getBtVoltar() { return btVoltar; }
    
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public void limparCampos() {
        tfNome.setText("");
        tfPreco.setText("");
        tfQtde.setText("");
    }
}
