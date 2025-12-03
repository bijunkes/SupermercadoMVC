package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.Produto;
import net.miginfocom.swing.MigLayout;

public class EditarProduto extends JDialog {

	private JTextField textFieldProduto;
	private JTextField textFieldPreco;
	private JTextField textFieldQtde;
	private JButton buttonSalvar;

	public EditarProduto(JFrame parent, Produto produto) {
		super(parent, "Editar Produto", true);

		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);

		getContentPane().setBackground(corFundo);
		setSize(500, 400);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(new MigLayout("", "30[500]30", "30[30][30][40][30][40][30][40]20[40]30"));

		JLabel lblEditarProduto = new JLabel("EDITAR PRODUTO");
		lblEditarProduto.setVerticalAlignment(SwingConstants.TOP);
		lblEditarProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarProduto.setForeground(verdeClaro);
		lblEditarProduto.setFont(new Font("Arial", Font.BOLD, 30));
		getContentPane().add(lblEditarProduto, "cell 0 0,alignx center,growy");

		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setForeground(verdeClaro);
		lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblProduto, "cell 0 1,grow");

		textFieldProduto = new JTextField(produto.getNome());
		textFieldProduto.setFont(new Font("Arial", Font.BOLD, 18));
		textFieldProduto.setForeground(corFundo);
		textFieldProduto.setBackground(verdeClaroTransparente);
		textFieldProduto.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		getContentPane().add(textFieldProduto, "cell 0 2,grow");

		JLabel lblPreco = new JLabel("PREÇO (R$)");
		lblPreco.setForeground(verdeClaro);
		lblPreco.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblPreco, "cell 0 3,alignx left,growy");

		textFieldPreco = new JTextField(String.valueOf(produto.getPreco()));
		textFieldPreco.setFont(new Font("Arial", Font.BOLD, 18));
		textFieldPreco.setForeground(corFundo);
		textFieldPreco.setBackground(verdeClaroTransparente);
		textFieldPreco.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		getContentPane().add(textFieldPreco, "cell 0 4,grow");

		JLabel lblQtde = new JLabel("QUANTIDADE");
		lblQtde.setForeground(verdeClaro);
		lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblQtde, "cell 0 5,alignx left,growy");

		textFieldQtde = new JTextField(String.valueOf(produto.getQtde()));
		textFieldQtde.setFont(new Font("Arial", Font.BOLD, 18));
		textFieldQtde.setForeground(corFundo);
		textFieldQtde.setBackground(verdeClaroTransparente);
		textFieldQtde.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		getContentPane().add(textFieldQtde, "cell 0 6,grow");

		buttonSalvar = new JButton("SALVAR");
		buttonSalvar.setOpaque(true);
		buttonSalvar.setForeground(corFundo);
		buttonSalvar.setFont(new Font("Arial", Font.BOLD, 18));
		buttonSalvar.setBorderPainted(false);
		buttonSalvar.setBackground(verdeClaro);
		getContentPane().add(buttonSalvar, "cell 0 7,alignx center,growy");
	}

	public String getNome() {
		return textFieldProduto.getText().trim();
	}

	public String getPreco() {
		return textFieldPreco.getText().trim();
	}

	public String getQtde() {
		return textFieldQtde.getText().trim();
	}

	public JButton getButtonSalvar() {
		return buttonSalvar;
	}

	public void adicionarAcaoSalvar(ActionListener listener) {
		buttonSalvar.addActionListener(listener);
	}

	public void mostrarMensagem(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
}
