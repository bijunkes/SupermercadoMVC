package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JFormattedTextField textFieldCpf;
	private JButton buttonEntrar;
	private JButton buttonVoltar;

	public Login() {
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);

		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(new MigLayout("fill, insets 0", "[200][200][300][200][200]", "30[][][][40][40][20][40][10]20"));

		JLabel labelLogin = new JLabel("LOGIN");
		labelLogin.setForeground(verdeClaro);
		labelLogin.setFont(new Font("Arial", Font.BOLD, 40));
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelLogin, "cell 2 0,growx,aligny bottom");

		JLabel lblNome = new JLabel("NOME");
		lblNome.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNome.setForeground(verdeClaro);
		lblNome.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNome, "cell 1 1,growx,aligny bottom");

		textFieldNome = new JTextField();
		textFieldNome.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldNome.setForeground(corFundo);
		textFieldNome.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldNome.setBackground(verdeClaroTransparente);
		add(textFieldNome, "cell 1 2 3 1,grow");

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(verdeClaro);
		lblCpf.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblCpf, "cell 1 3,growx,aligny bottom");

		try {
			MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
			textFieldCpf = new JFormattedTextField(cpfMask);
			textFieldCpf.setBorder(new EmptyBorder(10, 10, 10, 10));
			textFieldCpf.setForeground(corFundo);
			textFieldCpf.setFont(new Font("Arial", Font.BOLD, 16));
			textFieldCpf.setBackground(verdeClaroTransparente);
			add(textFieldCpf, "cell 1 4 3 1,grow");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		buttonEntrar = new JButton("ENTRAR");
		buttonEntrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonEntrar.setBackground(verdeClaro);
		buttonEntrar.setForeground(corFundo);
		buttonEntrar.setOpaque(true);
		buttonEntrar.setBorderPainted(false);
		add(buttonEntrar, "cell 2 6,alignx left,grow");

		buttonVoltar = new JButton(new ImageIcon(getClass().getResource("/icons/voltar.png")));
		buttonVoltar.setBackground(corFundo);
		buttonVoltar.setBorderPainted(false);
		add(buttonVoltar, "cell 4 6,grow");
	}

	public String getNome() {
		return textFieldNome.getText().trim();
	}

	public String getCpf() {
		return textFieldCpf.getText().trim();
	}

	public void adicionarAcaoEntrar(ActionListener listener) {
		buttonEntrar.addActionListener(listener);
	}

	public void adicionarAcaoVoltar(ActionListener listener) {
		buttonVoltar.addActionListener(listener);
	}
}
