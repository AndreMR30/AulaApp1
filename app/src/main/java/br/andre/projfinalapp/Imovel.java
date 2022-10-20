package br.andre.projfinalapp;

import java.io.Serializable;

public class Imovel implements Serializable {

    private int id;
    private String proprietario;
    private String telefone1;
    private String telefone2;
    private String condominio;
    private String endereco;
    private String data;
    private Double valor;
    private String observacao;
    private String statusAluguel;
    private String agenciador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getCondominio() {
        return condominio;
    }

    public void setCondominio(String condominio) {
        this.condominio = condominio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatusAluguel() {
        return statusAluguel;
    }

    public void setStatusAluguel(String statusAluguel) {
        this.statusAluguel = statusAluguel;
    }

    public String getAgenciador() {
        return agenciador;
    }

    public void setAgenciador(String agenciador) {
        this.agenciador = agenciador;
    }

    @Override
    public String toString() {
        return  "\nid: " + id + "\n" +
                "proprietario: " + proprietario + "\n" +
                "telefone1: " + telefone1 + "\n" +
                "telefone2: " + telefone2 + "\n" +
                "condominio: " + condominio + "\n" +
                "endereco: " + endereco + "\n" +
                "data: " + data + "\n" +
                "valor: " + valor + "\n" +
                "Observação: " + observacao + "\n" +
                "statusAluguel: " + statusAluguel + "\n" +
                "agenciador: " + agenciador + "\n" ;
    }
}
