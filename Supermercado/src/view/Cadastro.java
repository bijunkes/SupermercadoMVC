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
        setLayout(null);

        JLabel labelCadastro = new JLabel("CADASTRO");
        labelCadastro.setBounds(276, 94, 344, 47);
        labelCadastro.setForeground(verdeClaro);
        labelCadastro.setFont(new Font("Arial", Font.BOLD, 40));
        labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelCadastro);

        JLabel lblNome = new JLabel("NOME");
        lblNome.setForeground(verdeClaro);
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));
        lblNome.setBounds(200, 165, 59, 26);
        add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setBorder(new EmptyBorder(10, 10, 10, 10));
        textFieldNome.setForeground(corFundo);
        textFieldNome.setFont(new Font("Arial", Font.BOLD, 16));
        textFieldNome.setBackground(verdeClaroTransparente);
        textFieldNome.setBounds(200, 194, 500, 50);
        add(textFieldNome);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setForeground(verdeClaro);
        lblCpf.setFont(new Font("Arial", Font.BOLD, 16));
        lblCpf.setBounds(200, 245, 59, 26);
        add(lblCpf);

        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            textFieldCpf = new JFormattedTextField(cpfMask);
            textFieldCpf.setBorder(new EmptyBorder(10, 10, 10, 10));
            textFieldCpf.setForeground(corFundo);
            textFieldCpf.setFont(new Font("Arial", Font.BOLD, 16));
            textFieldCpf.setBackground(verdeClaroTransparente);
            textFieldCpf.setBounds(200, 274, 500, 50);
            add(textFieldCpf);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        radioButtonAdm = new JRadioButton("ADMINISTRADOR");
        radioButtonAdm.setBackground(verdeClaroTransparente);
        radioButtonAdm.setForeground(corFundo);
        radioButtonAdm.setFont(new Font("Arial", Font.BOLD, 16));
        radioButtonAdm.setBounds(200, 356, 500, 50);
        add(radioButtonAdm);

        buttonCadastrar = new JButton("CADASTRAR");
        buttonCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
        buttonCadastrar.setBounds(350, 454, 200, 50);
        buttonCadastrar.setBackground(verdeClaro);
        buttonCadastrar.setForeground(corFundo);
        buttonCadastrar.setBorderPainted(false);
        add(buttonCadastrar);

        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/voltar.png"));
        JButton buttonVoltar = new JButton(icon);
        buttonVoltar.setBounds(820, 530, 38, 40);
        buttonVoltar.setBackground(corFundo);
        buttonVoltar.setBorderPainted(false);
        buttonVoltar.addActionListener(e -> frame.mostrarInicio());
        add(buttonVoltar);
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
