package model;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private boolean admin;

    public Usuario(int id, String nome, String cpf, boolean admin) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.admin = admin;
    }

    public Usuario(String nome, String cpf, boolean admin) {
        this.nome = nome;
        this.cpf = cpf;
        this.admin = admin;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
