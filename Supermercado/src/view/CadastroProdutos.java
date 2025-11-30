package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import net.miginfocom.swing.MigLayout;

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
		setLayout(new MigLayout("", "50[400]50[400][400][400]50", "50[60][100][60][60][][100][40][100][40][100]30[][40][][][][]50[][][]50"));
		
				JLabel labelCadastro = new JLabel("CADASTRO");
				labelCadastro.setVerticalAlignment(SwingConstants.BOTTOM);
				labelCadastro.setForeground(verdeClaro);
				labelCadastro.setFont(new Font("Arial", Font.BOLD, 30));
				labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
				add(labelCadastro, "cell 0 0,growx,aligny bottom");
		
		JLabel lblPrecoTabela = new JLabel("PREÇO");
		lblPrecoTabela.setOpaque(true);
		lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoTabela.setForeground(new Color(37, 77, 50));
		lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrecoTabela.setBackground(new Color(208, 219, 151));
		add(lblPrecoTabela, "cell 2 0,alignx right,grow");
		
		JLabel lblQtdeTabela = new JLabel("QTDE");
		lblQtdeTabela.setOpaque(true);
		lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdeTabela.setForeground(new Color(37, 77, 50));
		lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblQtdeTabela.setBackground(new Color(208, 219, 151));
		add(lblQtdeTabela, "cell 3 0,alignx right,grow");
		
		JLabel labelDeProdutos = new JLabel("DE PRODUTOS");
		labelDeProdutos.setVerticalAlignment(SwingConstants.TOP);
		labelDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeProdutos.setForeground(new Color(208, 219, 151));
		labelDeProdutos.setFont(new Font("Arial", Font.BOLD, 30));
		add(labelDeProdutos, "cell 0 1,growx,aligny bottom");
		
		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduto.setForeground(new Color(208, 219, 151));
		lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblProduto, "cell 0 3,grow");

		tfNome = new JTextField();
		tfNome.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfNome.setToolTipText("PRODUTO");
		tfNome.setForeground(corFundo);
		tfNome.setFont(new Font("Arial", Font.BOLD, 16));
		tfNome.setBackground(verdeClaroTransparente);
		add(tfNome, "cell 0 5,grow");
		tfNome.setColumns(10);
		
		JLabel lblPreco = new JLabel("PREÇO (R$)");
		lblPreco.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPreco.setHorizontalAlignment(SwingConstants.LEFT);
		lblPreco.setForeground(new Color(208, 219, 151));
		lblPreco.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblPreco, "cell 0 7,grow");
		
		tfPreco = new JTextField();
		tfPreco.setToolTipText("PREÇO");
		tfPreco.setForeground(corFundo);
		tfPreco.setFont(new Font("Arial", Font.BOLD, 16));
		tfPreco.setColumns(10);
		tfPreco.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfPreco.setBackground(new Color(122, 148, 101));
		add(tfPreco, "cell 0 9,grow");

		JLabel lblQtde = new JLabel("QUANTIDADE");
		lblQtde.setVerticalAlignment(SwingConstants.BOTTOM);
		lblQtde.setHorizontalAlignment(SwingConstants.LEFT);
		lblQtde.setForeground(new Color(208, 219, 151));
		lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblQtde, "cell 0 11,grow");
        
        tfQtde = new JTextField();
        tfQtde.setToolTipText("QUANTIDADE");
        tfQtde.setForeground(corFundo);
        tfQtde.setFont(new Font("Arial", Font.BOLD, 16));
        tfQtde.setColumns(10);
        tfQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
        tfQtde.setBackground(new Color(122, 148, 101));
        add(tfQtde, "cell 0 12,grow");

        btCadastrar = new JButton("CADASTRAR");
        btCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
        btCadastrar.setBackground(verdeClaro);
        btCadastrar.setForeground(corFundo); 
        btCadastrar.setOpaque(true);
        btCadastrar.setBorderPainted(false);
        add(btCadastrar, "cell 0 16,alignx center,growy");
        
        JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setForeground(corFundo);
		lblProdutoTabela.setBackground(verdeClaro);
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblProdutoTabela, "cell 1 0,grow");
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setForeground(corFundo);
		table.setBackground(verdeClaroTransparente);
		table.setRowHeight(30);
		
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
		add(table, "cell 1 1 3 16,grow");
                
                        btEditar = new JButton("EDITAR");
                        btEditar.setBackground(new Color(208, 219, 151));
                        btEditar.setForeground(new Color(37, 77, 50));
                        btEditar.setOpaque(true);
                        btEditar.setBorderPainted(false);
                        btEditar.setFont(new Font("Arial", Font.BOLD, 18));
                        add(btEditar, "cell 1 17,grow");
                        
                                btExcluir = new JButton("EXCLUIR");
                                btExcluir.setBackground(verdeClaroTransparente);
                                btExcluir.setForeground(new Color(37, 77, 50));
                                btExcluir.setOpaque(true);
                                btExcluir.setBorderPainted(false);
                                btExcluir.setFont(new Font("Arial", Font.BOLD, 18));
                                add(btExcluir, "cell 2 17,grow");
                        
                                btVoltar = new JButton(new ImageIcon(getClass().getResource("/icons/voltar.png")));
                                btVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
                                btVoltar.setBackground(corFundo);
                                btVoltar.setForeground(verdeClaro);
                                btVoltar.setOpaque(true);
                                btVoltar.setBorderPainted(false);
                                add(btVoltar, "cell 3 17,grow");
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
