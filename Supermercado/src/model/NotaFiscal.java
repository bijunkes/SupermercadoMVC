package model;

import java.util.List;

public class NotaFiscal {
	private Usuario usuario;
	private List<Carrinho> produtos;
	private double total;

	public NotaFiscal(Usuario usuario, List<Carrinho> produtos) {
		this.usuario = usuario;
		this.produtos = produtos;
		calcularTotal();
	}

	private void calcularTotal() {
		total = 0.0;
		for (Carrinho c : produtos) {
			total += c.getPreco() * c.getQtde();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<Carrinho> getProdutos() {
		return produtos;
	}

	public double getTotal() {
		return total;
	}
}
