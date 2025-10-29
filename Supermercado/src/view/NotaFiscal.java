package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.Carrinho;
import model.Usuario;

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
        getContentPane().setLayout(null);

        JLabel lblNotaFiscal = new JLabel("NOTA FISCAL", SwingConstants.CENTER);
        lblNotaFiscal.setForeground(verdeClaro);
        lblNotaFiscal.setFont(new Font("Arial", Font.BOLD, 30));
        lblNotaFiscal.setBounds(100, 35, 300, 29);
        getContentPane().add(lblNotaFiscal);

        JLabel lblNome = new JLabel("NOME");
        lblNome.setForeground(verdeClaro);
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));
        lblNome.setBounds(75, 70, 220, 29);
        getContentPane().add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setEditable(false);
        textFieldNome.setBounds(75, 100, 350, 40);
        textFieldNome.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldNome.setBackground(verdeClaroTransparente);
        textFieldNome.setForeground(corFundo);
        textFieldNome.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        getContentPane().add(textFieldNome);

        JLabel lblCPF = new JLabel("CPF");
        lblCPF.setForeground(verdeClaro);
        lblCPF.setFont(new Font("Arial", Font.BOLD, 16));
        lblCPF.setBounds(75, 140, 220, 29);
        getContentPane().add(lblCPF);

        textFieldCPF = new JTextField();
        textFieldCPF.setEditable(false);
        textFieldCPF.setBounds(75, 170, 350, 40);
        textFieldCPF.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldCPF.setBackground(verdeClaroTransparente);
        textFieldCPF.setForeground(corFundo);
        textFieldCPF.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        getContentPane().add(textFieldCPF);

        JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setForeground(new Color(37, 77, 50));
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblProdutoTabela.setBackground(new Color(208, 219, 151));
		lblProdutoTabela.setBounds(75, 240, 120, 30);
		getContentPane().add(lblProdutoTabela);
        
        JLabel lblQtdeTabela = new JLabel("QTDE");
        lblQtdeTabela.setOpaque(true);
        lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
        lblQtdeTabela.setForeground(new Color(37, 77, 50));
        lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
        lblQtdeTabela.setBackground(new Color(208, 219, 151));
        lblQtdeTabela.setBounds(305, 240, 120, 30);
        getContentPane().add(lblQtdeTabela);
        
        JLabel lblPrecoTabela = new JLabel("PREÇO");
        lblPrecoTabela.setOpaque(true);
        lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrecoTabela.setForeground(new Color(37, 77, 50));
        lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
        lblPrecoTabela.setBackground(new Color(208, 219, 151));
        lblPrecoTabela.setBounds(190, 240, 120, 30);
        getContentPane().add(lblPrecoTabela);
        
        table = new JTable();
        table.setRowHeight(30);
        table.setForeground(new Color(37, 77, 50));
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setBackground(new Color(122, 148, 101));
        table.setBounds(75, 270, 350, 190);
        getContentPane().add(table);
        
        JLabel lblTotal = new JLabel("TOTAL (R$)");
        lblTotal.setVerticalAlignment(SwingConstants.BOTTOM);
        lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotal.setForeground(new Color(208, 219, 151));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setBounds(75, 460, 220, 29);
        getContentPane().add(lblTotal);

        textFieldTotal = new JTextField();
        textFieldTotal.setEditable(false);
        textFieldTotal.setBounds(75, 490, 350, 40);
        textFieldTotal.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldTotal.setBackground(verdeClaroTransparente);
        textFieldTotal.setForeground(corFundo);
        textFieldTotal.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        getContentPane().add(textFieldTotal);

        setModal(true);
    }

    public void mostrarNota(Usuario usuario, List<Carrinho> produtos) {
        textFieldNome.setText(usuario.getNome());
        textFieldCPF.setText(usuario.getCpf());

        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Produto", "Preço (R$)", "Qtde"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        double total = 0.0;
        for (Carrinho c : produtos) {
            if (c.getProduto() != null) {
                modelo.addRow(new Object[]{
                    c.getProduto().getNome(),
                    c.getProduto().getPreco(),
                    c.getQtde()
                });
                total += c.getProduto().getPreco() * c.getQtde();
            }
        }



        table.setModel(modelo);
        textFieldTotal.setText(String.format("%.2f", total));

        setVisible(true);
    }
}
