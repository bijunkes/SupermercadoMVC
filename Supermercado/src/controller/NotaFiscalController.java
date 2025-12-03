package controller;

import view.NotaFiscal;
import model.Usuario;
import model.Carrinho;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NotaFiscalController {

	private JFrame frame;

	public NotaFiscalController(JFrame frame) {
		this.frame = frame;
	}

	public void emitirNota(Usuario usuario, List<Carrinho> produtos) {
		try {

			if (usuario == null) {
				JOptionPane.showMessageDialog(frame, "Erro: nenhum usuário informado.");
				return;
			}

			if (produtos == null) {
				JOptionPane.showMessageDialog(frame, "Erro: lista de produtos nula.");
				return;
			}

			if (produtos.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Não há produtos no carrinho para emitir a nota fiscal.");
				return;
			}

			NotaFiscal nota = new NotaFiscal(frame);
			nota.mostrarNota(usuario, produtos);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame,
					"Ocorreu um erro inesperado ao emitir a nota fiscal. \nDetalhes: " + ex.getMessage());
		}
	}
}
