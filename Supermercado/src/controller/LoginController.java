package controller;

import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.Login;

public class LoginController {
    private Login view;
    private Frame frame;
    private UsuarioDAO dao;

    public LoginController(Login view, Frame frame) {
        this.view = view;
        this.frame = frame;
        this.dao = new UsuarioDAO();

        this.view.adicionarAcaoEntrar(e -> logarUsuario());
        this.view.adicionarAcaoVoltar(e -> frame.mostrarInicio());
    }

    private void logarUsuario() {
        String nome = view.getNome();
        String cpf = view.getCpf();

        if (nome.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos!");
            return;
        }

        Usuario usuario = dao.logarUsuario(nome, cpf);
        if (usuario != null) {
            frame.setUsuarioLogado(usuario);
            if (usuario.isAdmin()) frame.mostrarCadastroProdutos();
            else frame.mostrarCompras();
        } else {
            JOptionPane.showMessageDialog(view, "Nome ou CPF inválido!");
        }
    }
}
