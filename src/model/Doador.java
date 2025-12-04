package model;

import java.time.LocalDate;

public class Doador extends Pessoa{

    private String tipoPessoa;
    private LocalDate dataCadastro;
    private boolean ativo;

    public Doador() {
        super();
        this.dataCadastro = LocalDate.now();
        this.ativo = true;

    }

    public Doador(String nome, String cpf, String telefone, String email, String endereco,
                  String tipoPessoa, LocalDate dataCadastro, boolean ativo) {
        super(nome, cpf, telefone, email, endereco);
        this.tipoPessoa = tipoPessoa;
        this.dataCadastro = LocalDate.now();
        this.ativo = true;

    }
    public Doador(String nome, String cpf, String telefone, String email, String endereco,
                  String tipoPessoa) {

        super(nome, cpf, telefone, email, endereco);
        this.tipoPessoa = tipoPessoa;
        this.dataCadastro = LocalDate.now();
        this.ativo = true;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Doador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", tipoPessoa='" + tipoPessoa + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", ativo=" + ativo +
                '}';
    }
}
