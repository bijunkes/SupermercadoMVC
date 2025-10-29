package model;

public class Produto {
    private int id;
    private String nome;
    private int qtde;
    private double preco;

    public Produto(int id, String nome, double preco, int qtde) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtde = qtde;
    }

    public Produto(String nome, double preco, int qtde) {
        this(0, nome, preco, qtde);
    }

    public int getId() {
    	return id;
    }
    
    public void setId(int id) { 
    	this.id = id; 
    }

    public String getNome() { 
    	return nome; 
    }
    
    public void setNome(String nome) { 
    	this.nome = nome; 
    }

    public int getQtde() { 
    	return qtde; 
    }
    public void setQtde(int qtde) { 
    	this.qtde = qtde; 
    }

    public double getPreco() { 
    	return preco; 
    }
    
    public void setPreco(double preco) { 
    	this.preco = preco; 
    }
}
