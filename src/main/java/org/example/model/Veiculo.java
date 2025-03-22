package org.example.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cadVeiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String marca;
    private String modelo;
    private String ano;
    private String cor;
    private Double preco;
    private Integer qtde;

    public Veiculo(String marca, String modelo, String ano, String cor, Double preco, Integer qtde) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.qtde = qtde;
    }

    public Veiculo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtde() { return qtde; }

    public void setQtde(Integer qtde) { this.qtde = qtde; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            sb.append("Veículo Código: ").append(id)
                .append("\nMarca: ").append(marca)
                .append("\nModelo: ").append(modelo)
                .append("\nAno: ").append(ano)
                .append("\nCor: ").append(cor)
                .append("\nPreço: ").append(preco)
                .append("\nQuantidade: ").append(qtde)
                .append("\n");
        return sb.toString();
    }
}