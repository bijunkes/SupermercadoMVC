package controller;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.CarrinhoDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.Cadastro;
import view.CadastroProdutos;
import view.Carrinho;
import view.Compras;
import view.Inicio;
import view.Login;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private Usuario usuarioLogado;

    private ProdutoController produtoController;

    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
        setContentPane(contentPane);
        this.setLocationRelativeTo(null);

        mostrarInicio();
        pack();
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public ProdutoController getProdutoController() {
        return produtoController;
    }

    public void mostrarInicio() {
        contentPane.removeAll();
        Inicio inicio = new Inicio();
        new InicioController(inicio, this);
        contentPane.add(inicio, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void mostrarCadastro() {
        contentPane.removeAll();
        Cadastro cadastro = new Cadastro(this);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        new CadastroController(cadastro, usuarioDAO, this);
        contentPane.add(cadastro, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void mostrarLogin() {
        contentPane.removeAll();
        Login login = new Login();
        new LoginController(login, this);
        contentPane.add(login, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void mostrarCadastroProdutos() {
        contentPane.removeAll();
        CadastroProdutos cadastroProdutos = new CadastroProdutos();
        produtoController = new ProdutoController(cadastroProdutos, this); 
        contentPane.add(cadastroProdutos, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void mostrarCompras() {
        contentPane.removeAll();

        if (produtoController == null) {
            produtoController = new ProdutoController(new CadastroProdutos(), this);
        }

        Compras viewCompras = new Compras();
        new ComprasController(viewCompras, this);

        contentPane.add(viewCompras, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void mostrarCarrinho() {
        contentPane.removeAll();
        Carrinho carrinhoView = new Carrinho();
        new CarrinhoController(
            carrinhoView,
            this.getUsuarioLogado(),
            this.getProdutoController(),
            new CarrinhoDAO(),
            this
        );
        contentPane.add(carrinhoView, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }
}
