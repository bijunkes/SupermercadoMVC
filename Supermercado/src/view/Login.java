package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.awt.event.ActionListener;

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
        setLayout(null);

        JLabel labelLogin = new JLabel("LOGIN");
        labelLogin.setBounds(276, 130, 344, 47);
        labelLogin.setForeground(verdeClaro);
        labelLogin.setFont(new Font("Arial", Font.BOLD, 40));
        labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelLogin);

        JLabel lblNome = new JLabel("NOME");
        lblNome.setForeground(verdeClaro);
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));
        lblNome.setBounds(200, 208, 108, 19);
        add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setBorder(new EmptyBorder(10, 10, 10, 10));
        textFieldNome.setForeground(corFundo);
        textFieldNome.setFont(new Font("Arial", Font.BOLD, 16));
        textFieldNome.setBackground(verdeClaroTransparente);
        textFieldNome.setBounds(200, 230, 500, 50);
        add(textFieldNome);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setForeground(verdeClaro);
        lblCpf.setFont(new Font("Arial", Font.BOLD, 16));
        lblCpf.setBounds(200, 290, 108, 19);
        add(lblCpf);

        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            textFieldCpf = new JFormattedTextField(cpfMask);
            textFieldCpf.setBorder(new EmptyBorder(10, 10, 10, 10));
            textFieldCpf.setForeground(corFundo);
            textFieldCpf.setFont(new Font("Arial", Font.BOLD, 16));
            textFieldCpf.setBackground(verdeClaroTransparente);
            textFieldCpf.setBounds(200, 310, 500, 50);
            add(textFieldCpf);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        buttonEntrar = new JButton("ENTRAR");
        buttonEntrar.setFont(new Font("Arial", Font.BOLD, 20));
        buttonEntrar.setBounds(350, 430, 200, 50);
        buttonEntrar.setBackground(verdeClaro);
        buttonEntrar.setForeground(corFundo); 
        buttonEntrar.setOpaque(true);
        buttonEntrar.setBorderPainted(false);
        add(buttonEntrar);

        buttonVoltar = new JButton(new ImageIcon(getClass().getResource("/icons/voltar.png")));
        buttonVoltar.setBounds(820, 530, 38, 40);
        buttonVoltar.setBackground(corFundo);
        buttonVoltar.setBorderPainted(false);
        add(buttonVoltar);
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
