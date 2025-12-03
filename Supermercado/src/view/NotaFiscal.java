package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.Carrinho;
import model.Usuario;
import net.miginfocom.swing.MigLayout;

public class NotaFiscal extends JDialog {

	private JTextField textFieldNome;
	private JTextField textFieldCPF;
	private JTextField textFieldTotal;
	private JTable table;

	public NotaFiscal(JFrame parent) {
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);

		getContentPane().setBackground(corFundo);
		setSize(500, 600);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(new MigLayout("", "30[grow][grow][grow]30",
				"30[29px][29px][40px][29px][40px][30px][190px][29px][40px]30"));

		JLabel lblNotaFiscal = new JLabel("NOTA FISCAL", SwingConstants.CENTER);
		lblNotaFiscal.setForeground(verdeClaro);
		lblNotaFiscal.setFont(new Font("Arial", Font.BOLD, 30));
		getContentPane().add(lblNotaFiscal, "cell 0 0 3 1,grow");

		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(verdeClaro);
		lblNome.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblNome, "cell 0 1,grow");

		textFieldNome = new JTextField();
		textFieldNome.setEditable(false);
		textFieldNome.setFont(new Font("Arial", Font.BOLD, 18));
		textFieldNome.setBackground(verdeClaroTransparente);
		textFieldNome.setForeground(corFundo);
		textFieldNome.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		getContentPane().add(textFieldNome, "cell 0 2 3 1,grow");

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setForeground(verdeClaro);
		lblCPF.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblCPF, "cell 0 3,grow");

		textFieldCPF = new JTextField();
		textFieldCPF.setEditable(false);
		textFieldCPF.setFont(new Font("Arial", Font.BOLD, 18));
		textFieldCPF.setBackground(verdeClaroTransparente);
		textFieldCPF.setForeground(corFundo);
		textFieldCPF.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		getContentPane().add(textFieldCPF, "cell 0 4 3 1,grow");

		JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setForeground(new Color(37, 77, 50));
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblProdutoTabela.setBackground(new Color(208, 219, 151));
		getContentPane().add(lblProdutoTabela, "cell 0 5,alignx left,grow");

		JLabel lblPrecoTabela = new JLabel("PREÇO");
		lblPrecoTabela.setOpaque(true);
		lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoTabela.setForeground(new Color(37, 77, 50));
		lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrecoTabela.setBackground(new Color(208, 219, 151));
		getContentPane().add(lblPrecoTabela, "cell 1 5,alignx center,grow");

		JLabel lblQtdeTabela = new JLabel("QTDE");
		lblQtdeTabela.setOpaque(true);
		lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdeTabela.setForeground(new Color(37, 77, 50));
		lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblQtdeTabela.setBackground(new Color(208, 219, 151));
		getContentPane().add(lblQtdeTabela, "cell 2 5,grow");

		table = new JTable();
		table.setRowHeight(30);
		table.setForeground(new Color(37, 77, 50));
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setBackground(new Color(122, 148, 101));
		getContentPane().add(table, "cell 0 6 3 1,grow");

		JLabel lblTotal = new JLabel("TOTAL (R$)");
		lblTotal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setForeground(new Color(208, 219, 151));
		lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblTotal, "cell 0 7,grow");

		textFieldTotal = new JTextField();
		textFieldTotal.setEditable(false);
		textFieldTotal.setFont(new Font("Arial", Font.BOLD, 18));
		textFieldTotal.setBackground(verdeClaroTransparente);
		textFieldTotal.setForeground(corFundo);
		textFieldTotal.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		getContentPane().add(textFieldTotal, "cell 0 8 3 1,grow");

		setModal(true);
	}

	public void mostrarNota(Usuario usuario, List<Carrinho> produtos) {
		textFieldNome.setText(usuario.getNome());
		textFieldCPF.setText(usuario.getCpf());

		DefaultTableModel modelo = new DefaultTableModel(new Object[] { "Produto", "Preço (R$)", "Qtde" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		double total = 0.0;
		for (Carrinho c : produtos) {
			if (c.getProduto() != null) {
				modelo.addRow(new Object[] { c.getProduto().getNome(), c.getProduto().getPreco(), c.getQtde() });
				total += c.getProduto().getPreco() * c.getQtde();
			}
		}

		table.setModel(modelo);
		textFieldTotal.setText(String.format("%.2f", total));

		setVisible(true);
	}
}
