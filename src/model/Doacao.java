package model;

import java.time.LocalDate;

public class Doacao {

    private int id;
    private int idDoador;
    private String nomeDoador;
    private String tipo;
    private String descricao;
    private double valor;
    private int quantidade;
    private LocalDate dataDoacao;
    private String observacoes;

    public Doacao() {
        this.dataDoacao = LocalDate.now();

    }

    // Construtor para doação em dinheiro
    public Doacao(int idDoador, String nomeDoador,
                  String tipo, String descricao, double valor) {
        this.idDoador = idDoador;
        this.nomeDoador = nomeDoador;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.dataDoacao = LocalDate.now();
    }

    // Construtor para doação de itens
    public Doacao(int idDoador, String nomeDoador, String tipo,
                  String descricao, int quantidade, double valor) {
        this.idDoador = idDoador;
        this.nomeDoador = nomeDoador;
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDoacao = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(int idDoador) {
        this.idDoador = idDoador;
    }

    public String getNomeDoador() {
        return nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Doacao{" +
                "id=" + id +
                ", nomeDoador='" + nomeDoador + '\'' +
                ", tipo='" + tipo + '\'' +
                ", valor=" + String.format("%.2f", valor) +
                ", quantidade=" + quantidade +
                ", dataDoacao=" + dataDoacao +
                '}';
    }
}
