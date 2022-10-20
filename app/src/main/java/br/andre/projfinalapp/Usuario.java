package br.andre.projfinalapp;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String nome;
    private long cpf;
    private String telefone;
    private String usuario;
    private String senha;

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

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return  "\nid: " + id + "\n" +
                "nome: " + nome + "\n" +
                "cpf: " + cpf + "\n"+
                "telefone: " + telefone + "\n" +
                "usuario: " + usuario + "\n" +
                "senha: " + senha + "\n";
    }
}
