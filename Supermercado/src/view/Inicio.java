package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;

public class Inicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton buttonCadastrar;
	private JButton buttonEntrar;
	private JButton buttonLogout;

	public Inicio() {
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);

		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(new MigLayout("fill, insets 0", "50[grow][grow][grow]", "30[][40][][]50[20][][]20"));

		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logout.png"));

		JLabel labelSupermercado = new JLabel("SUPERMERCADO");
		labelSupermercado.setVerticalAlignment(SwingConstants.BOTTOM);
		labelSupermercado.setForeground(verdeClaro);
		labelSupermercado.setFont(new Font("Arial", Font.BOLD, 40));
		labelSupermercado.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelSupermercado, "cell 1 1,alignx center,aligny center");

		buttonCadastrar = new JButton("CADASTRAR");
		buttonCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonCadastrar.setBackground(verdeClaro);
		buttonCadastrar.setForeground(corFundo);
		buttonCadastrar.setOpaque(true);
		buttonCadastrar.setBorderPainted(false);
		add(buttonCadastrar, "cell 1 3,grow");

		buttonEntrar = new JButton("ENTRAR");
		buttonEntrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonEntrar.setBackground(verdeClaro);
		buttonEntrar.setForeground(corFundo);
		buttonEntrar.setOpaque(true);
		buttonEntrar.setBorderPainted(false);
		add(buttonEntrar, "cell 1 4,grow");
		buttonLogout = new JButton(icon);
		buttonLogout.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonLogout.setBackground(corFundo);
		buttonLogout.setForeground(verdeClaro);
		buttonLogout.setOpaque(true);
		buttonLogout.setBorderPainted(false);
		add(buttonLogout, "cell 2 5,grow");
	}

	public void adicionarAcaoCadastrar(java.awt.event.ActionListener listener) {
		buttonCadastrar.addActionListener(listener);
	}

	public void adicionarAcaoEntrar(java.awt.event.ActionListener listener) {
		buttonEntrar.addActionListener(listener);
	}

	public void adicionarAcaoLogout(java.awt.event.ActionListener listener) {
		buttonLogout.addActionListener(listener);
	}
}
