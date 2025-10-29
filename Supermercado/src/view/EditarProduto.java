package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.Produto;

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
        getContentPane().setLayout(null);

        JLabel lblEditarProduto = new JLabel("EDITAR PRODUTO");
        lblEditarProduto.setVerticalAlignment(SwingConstants.TOP);
        lblEditarProduto.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditarProduto.setForeground(verdeClaro);
        lblEditarProduto.setFont(new Font("Arial", Font.BOLD, 30));
        lblEditarProduto.setBounds(100, 35, 300, 29);
        getContentPane().add(lblEditarProduto);

        JLabel lblProduto = new JLabel("PRODUTO");
        lblProduto.setForeground(verdeClaro);
        lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
        lblProduto.setBounds(75, 70, 220, 29);
        getContentPane().add(lblProduto);

        textFieldProduto = new JTextField(produto.getNome());
        textFieldProduto.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldProduto.setForeground(corFundo);
        textFieldProduto.setBackground(verdeClaroTransparente);
        textFieldProduto.setBounds(75, 100, 350, 40);
        textFieldProduto.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        getContentPane().add(textFieldProduto);

        JLabel lblPreco = new JLabel("PREÇO (R$)");
        lblPreco.setForeground(verdeClaro);
        lblPreco.setFont(new Font("Arial", Font.BOLD, 16));
        lblPreco.setBounds(75, 140, 220, 29);
        getContentPane().add(lblPreco);

        textFieldPreco = new JTextField(String.valueOf(produto.getPreco()));
        textFieldPreco.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldPreco.setForeground(corFundo);
        textFieldPreco.setBackground(verdeClaroTransparente);
        textFieldPreco.setBounds(75, 170, 350, 40);
        textFieldPreco.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        getContentPane().add(textFieldPreco);

        JLabel lblQtde = new JLabel("QUANTIDADE");
        lblQtde.setForeground(verdeClaro);
        lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
        lblQtde.setBounds(75, 210, 220, 29);
        getContentPane().add(lblQtde);

        textFieldQtde = new JTextField(String.valueOf(produto.getQtde()));
        textFieldQtde.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldQtde.setForeground(corFundo);
        textFieldQtde.setBackground(verdeClaroTransparente);
        textFieldQtde.setBounds(75, 240, 350, 40);
        textFieldQtde.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        getContentPane().add(textFieldQtde);

        buttonSalvar = new JButton("SALVAR");
        buttonSalvar.setOpaque(true);
        buttonSalvar.setForeground(corFundo);
        buttonSalvar.setFont(new Font("Arial", Font.BOLD, 18));
        buttonSalvar.setBorderPainted(false);
        buttonSalvar.setBackground(verdeClaro);
        buttonSalvar.setBounds(173, 300, 150, 40);
        getContentPane().add(buttonSalvar);
    }

    public String getNome() { return textFieldProduto.getText().trim(); }
    public String getPreco() { return textFieldPreco.getText().trim(); }
    public String getQtde() { return textFieldQtde.getText().trim(); }

    public JButton getButtonSalvar() { return buttonSalvar; }

    public void adicionarAcaoSalvar(ActionListener listener) {
        buttonSalvar.addActionListener(listener);
    }

    public void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
