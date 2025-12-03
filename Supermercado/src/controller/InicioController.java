package controller;

import view.Inicio;

public class InicioController {

	private Inicio view;
	private Frame frame;

	public InicioController(Inicio view, Frame frame) {
		this.view = view;
		this.frame = frame;

		this.view.adicionarAcaoCadastrar(e -> frame.mostrarCadastro());
		this.view.adicionarAcaoEntrar(e -> frame.mostrarLogin());
		this.view.adicionarAcaoLogout(e -> frame.dispose());
	}
}
