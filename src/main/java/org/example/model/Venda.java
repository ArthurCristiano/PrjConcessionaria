package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cadVenda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Double valorVenda;
    private LocalDate dataInc = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Veiculo veiculo;

    public Venda(Cliente cliente, Funcionario funcionario, Veiculo veiculo, Double valorVenda) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.veiculo = veiculo;
        this.valorVenda = valorVenda;
    }

    public Venda() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setDataInc(LocalDate dataInc) {
        this.dataInc = dataInc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venda Código: ").append(id)
                .append("\nCliente: ").append(cliente.getNomeCliente())
                .append("\nFuncionário: ").append(funcionario.getNomeFuncionario())
                .append("\nVeículo: ").append(veiculo.getMarca()).append(" ").append(veiculo.getModelo())
                .append("\nPreço: ").append(getValorVenda())
                .append("\n");
        return sb.toString();
    }
}