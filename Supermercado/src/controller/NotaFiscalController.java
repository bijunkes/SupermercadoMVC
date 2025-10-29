package controller;

import view.NotaFiscal;
import model.Usuario;
import model.Carrinho;
import java.util.List;
import javax.swing.JFrame;

public class NotaFiscalController {

    private JFrame frame;

    public NotaFiscalController(JFrame frame) {
        this.frame = frame;
    }

    public void emitirNota(Usuario usuario, List<Carrinho> produtos) {
        NotaFiscal nota = new NotaFiscal(frame);
        nota.mostrarNota(usuario, produtos);
    }
}
