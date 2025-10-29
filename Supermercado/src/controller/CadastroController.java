package controller;

import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.Cadastro;

public class CadastroController {

    private final Cadastro view;
    private final UsuarioDAO usuarioDAO;
    private final Frame frame;

    public CadastroController(Cadastro view, UsuarioDAO usuarioDAO, Frame frame) {
        this.view = view;
        this.usuarioDAO = usuarioDAO;
        this.frame = frame;

        this.view.adicionarAcaoCadastrar(e -> cadastrarUsuario());
    }

    private void cadastrarUsuario() {
        String nome = view.getNome();
        String cpf = view.getCpf();
        boolean admin = view.isAdmin();

        if (nome.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = new Usuario(0, nome, cpf, admin);

        boolean sucesso = usuarioDAO.cadastrarUsuario(usuario);

        if (sucesso) {
            JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso!");
            frame.mostrarInicio();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao cadastrar usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
