package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.Font;

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
        setLayout(null);
        
        JLabel labelSupermercado = new JLabel("SUPERMERCADO");
        labelSupermercado.setBounds(284, 150, 344, 47);
        labelSupermercado.setForeground(verdeClaro);
        labelSupermercado.setFont(new Font("Arial", Font.BOLD, 40));
        labelSupermercado.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelSupermercado);
        
        buttonCadastrar = new JButton("CADASTRAR");
        buttonCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
        buttonCadastrar.setBounds(250, 300, 400, 50);
        buttonCadastrar.setBackground(verdeClaro);
        buttonCadastrar.setForeground(corFundo); 
        buttonCadastrar.setOpaque(true);
        buttonCadastrar.setBorderPainted(false);
        add(buttonCadastrar);
        
        buttonEntrar = new JButton("ENTRAR");
        buttonEntrar.setFont(new Font("Arial", Font.BOLD, 20));
        buttonEntrar.setBounds(250, 400, 400, 50);
        buttonEntrar.setBackground(verdeClaro);
        buttonEntrar.setForeground(corFundo); 
        buttonEntrar.setOpaque(true);
        buttonEntrar.setBorderPainted(false);
        add(buttonEntrar);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logout.png"));
        buttonLogout = new JButton(icon);
        buttonLogout.setBounds(820, 530, 38, 40);
        buttonLogout.setBackground(corFundo);
        buttonLogout.setForeground(verdeClaro);
        buttonLogout.setOpaque(true);
        buttonLogout.setBorderPainted(false);
        add(buttonLogout);
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
