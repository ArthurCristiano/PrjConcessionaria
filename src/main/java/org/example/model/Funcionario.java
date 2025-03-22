package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cadFuncionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nomeFuncionario;
    private String docFuncionario;
    private double salario;

    public Funcionario(String nomeFuncionario, String docFuncionario, double salario) {
        this.nomeFuncionario = nomeFuncionario;
        this.docFuncionario = docFuncionario;
        this.salario = salario;
    }

    public Funcionario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getDocFuncionario() {
        return docFuncionario;
    }

    public void setDocFuncionario(String docFuncionario) {
        this.docFuncionario = docFuncionario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            sb.append("Funcionário Código: ").append(id)
                .append("\nNome: ").append(nomeFuncionario)
                .append("\nDocumento: ").append(docFuncionario)
                .append("\nSalário: ").append(salario)
                .append('\n');
        return sb.toString();
    }
}