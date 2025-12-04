package model;

import java.time.LocalDate;

public class Funcionario extends Pessoa{

    private String cargo;
    private double salario;
    private LocalDate dataContratacao;
    private Turnos turno;
    private boolean ativo;

    public Funcionario(){
        super();
        this.dataContratacao = LocalDate.now();
        this.ativo = true;
    }

    public Funcionario(String nome, String cpf, String telefone, String email, String endereco,
                       String cargo, double salario, Turnos turno) {
        super(nome, cpf, telefone, email, endereco);
        this.cargo = cargo;
        this.salario = salario;
        this.turno = turno;
        this.dataContratacao = LocalDate.now();
        this.ativo = true;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Turnos getTurno() {
        return turno;
    }

    public void setTurno(Turnos turno) {
        this.turno = turno;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + String.format("%.2f", salario) +
                ", turno=" + turno +
                ", dataContratacao=" + dataContratacao +
                ", ativo=" + ativo +
                '}';
    }
}
