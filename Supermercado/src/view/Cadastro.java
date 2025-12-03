package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.Frame;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionEvent;

public class Cadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JFormattedTextField textFieldCpf;
	private JRadioButton radioButtonAdm;
	private JButton buttonCadastrar;

	public Cadastro(Frame frame) {
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);

		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(new MigLayout("fill, insets 0, wrap 1", "[200]", "30[][][20][][20][][30][][]"));

		JLabel labelCadastro = new JLabel("CADASTRO");
		labelCadastro.setVerticalAlignment(SwingConstants.BOTTOM);
		labelCadastro.setForeground(verdeClaro);
		labelCadastro.setFont(new Font("Arial", Font.BOLD, 40));
		labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelCadastro, "cell 2 0,growx,aligny bottom");

		JLabel lblNome = new JLabel("NOME");
		lblNome.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNome.setForeground(verdeClaro);
		lblNome.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNome, "cell 1 1,grow");

		textFieldNome = new JTextField();
		textFieldNome.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldNome.setForeground(corFundo);
		textFieldNome.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldNome.setBackground(verdeClaroTransparente);
		add(textFieldNome, "cell 1 2 3 1,grow");

		try {
			MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
			JLabel lblCpf = new JLabel("CPF");
			lblCpf.setVerticalAlignment(SwingConstants.BOTTOM);
			lblCpf.setForeground(verdeClaro);
			lblCpf.setFont(new Font("Arial", Font.BOLD, 16));
			add(lblCpf, "cell 1 3,grow");
			textFieldCpf = new JFormattedTextField(cpfMask);
			textFieldCpf.setBorder(new EmptyBorder(10, 10, 10, 10));
			textFieldCpf.setForeground(corFundo);
			textFieldCpf.setFont(new Font("Arial", Font.BOLD, 16));
			textFieldCpf.setBackground(verdeClaroTransparente);
			add(textFieldCpf, "cell 1 4 3 1,grow");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JLabel lblVazio = new JLabel(" ");
		lblVazio.setForeground(verdeClaro);
		lblVazio.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblVazio, "cell 1 5,grow");

		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/voltar.png"));

		radioButtonAdm = new JRadioButton("ADMINISTRADOR");
		radioButtonAdm.setBackground(verdeClaroTransparente);
		radioButtonAdm.setForeground(corFundo);
		radioButtonAdm.setFont(new Font("Arial", Font.BOLD, 16));
		add(radioButtonAdm, "cell 1 6 3 1,grow");

		JButton buttonVoltar = new JButton(icon);
		buttonVoltar.setBackground(corFundo);
		buttonVoltar.setBorderPainted(false);
		buttonVoltar.addActionListener(e -> frame.mostrarInicio());

		buttonCadastrar = new JButton("CADASTRAR");
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonCadastrar.setBackground(verdeClaro);
		buttonCadastrar.setForeground(corFundo);
		buttonCadastrar.setBorderPainted(false);
		add(buttonCadastrar, "cell 2 8,alignx center,grow");
		add(buttonVoltar, "cell 4 8,grow");
	}

	public String getNome() {
		return textFieldNome.getText();
	}

	public String getCpf() {
		return textFieldCpf.getText();
	}

	public boolean isAdmin() {
		return radioButtonAdm.isSelected();
	}

	public void adicionarAcaoCadastrar(ActionListener listener) {
		buttonCadastrar.addActionListener(listener);
	}
}
