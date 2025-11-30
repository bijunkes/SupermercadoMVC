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
        try {
            String nome = view.getNome().trim();
            String cpf = view.getCpf().trim();
            boolean admin = view.isAdmin();

            if (nome.isEmpty() || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                JOptionPane.showMessageDialog(view, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario existente = usuarioDAO.logarUsuario(nome, cpf);
            if (existente != null) {
                JOptionPane.showMessageDialog(view, 
                    "Já existe um usuário cadastrado com esse nome e CPF.", 
                    "Aviso", 
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Usuario usuario = new Usuario(0, nome, cpf, admin);

            boolean sucesso = usuarioDAO.cadastrarUsuario(usuario);

            if (sucesso) {
                JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso.");
                frame.mostrarInicio();
            } else {
                JOptionPane.showMessageDialog(view, 
                    "Erro ao cadastrar usuário. Verifique os dados e tente novamente.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                "Ocorreu um erro inesperado ao processar o cadastro.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }
}
