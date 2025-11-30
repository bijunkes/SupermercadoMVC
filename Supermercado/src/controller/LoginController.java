package controller;

import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.Login;

public class LoginController {
    private final Login view;
    private final Frame frame;
    private final UsuarioDAO dao;

    public LoginController(Login view, Frame frame) {
        this.view = view;
        this.frame = frame;
        this.dao = new UsuarioDAO();

        this.view.adicionarAcaoEntrar(e -> logarUsuario());
        this.view.adicionarAcaoVoltar(e -> frame.mostrarInicio());
    }

    private void logarUsuario() {
        try {
            String nome = view.getNome().trim();
            String cpf = view.getCpf().trim();

            if (nome.isEmpty() || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                JOptionPane.showMessageDialog(view, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario usuario = dao.logarUsuario(nome, cpf);

            if (usuario != null) {
                frame.setUsuarioLogado(usuario);

                if (usuario.isAdmin()) {
                    frame.mostrarCadastroProdutos();
                } else {
                    frame.mostrarCompras();
                }

            } else {
                JOptionPane.showMessageDialog(view, "Nome ou CPF inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                "Ocorreu um erro ao tentar fazer login. Tente novamente.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }
}
