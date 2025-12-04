package model;

import java.time.LocalDate;
import java.time.Period;

public class Idoso extends Pessoa{

    private LocalDate dataNascimento;
    private String necessidadesEspeciais;
    private String contatoEmergencia;
    private String telefoneEmergencia;
    private LocalDate dataIngresso;

    public Idoso() {
        super();
        this.dataIngresso = LocalDate.now();

    }

    public Idoso(String nome, String cpf, String telefone, String email, String endereco, LocalDate dataNascimento,
                 String contatoEmergencia, String telefoneEmergencia, LocalDate dataIngresso) {
        super(nome, cpf, telefone, email, endereco);
        this.dataNascimento = dataNascimento;
        this.contatoEmergencia = contatoEmergencia;
        this.telefoneEmergencia = telefoneEmergencia;
        this.dataIngresso = LocalDate.now();

    }

    public Idoso(String nome, String cpf, String telefone, String email, String endereco,
                 LocalDate dataNascimento, String contatoEmergencia, String telefoneEmergencia) {

        super(nome, cpf, telefone, email, endereco);

        this.dataNascimento = dataNascimento;
        this.contatoEmergencia = contatoEmergencia;
        this.telefoneEmergencia = telefoneEmergencia;
        this.dataIngresso = LocalDate.now(); // sempre a data atual
    }

    public int getIdade() {
        if (dataNascimento == null) return 0;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefoneEmergencia() {
        return telefoneEmergencia;
    }

    public void setTelefoneEmergencia(String telefoneEmergencia) {
        this.telefoneEmergencia = telefoneEmergencia;
    }

    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }

    public String getNecessidadesEspeciais() {
        return necessidadesEspeciais;
    }

    public void setNecessidadesEspeciais(String necessidadesEspeciais) {
        this.necessidadesEspeciais = necessidadesEspeciais;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    @Override
    public String toString() {
        return "Idoso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", idade=" + getIdade() + " anos" +
                ", contatoEmergencia='" + contatoEmergencia + '\'' +
                ", dataIngresso=" + dataIngresso +
                '}';
    }
}
