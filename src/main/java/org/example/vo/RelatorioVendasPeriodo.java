package org.example.vo;

public class RelatorioVendasPeriodo {

    private String nomeFuncionario;
    private String nomeCliente;
    private String marca;
    private String modelo;
    private Double valorVenda;

    public RelatorioVendasPeriodo(String nomeFuncionario, String nomeCliente, String marca, String modelo, Double valorVenda) {
        this.nomeFuncionario = nomeFuncionario;
        this.nomeCliente = nomeCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.valorVenda = valorVenda;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nRelatório de Vendas por Período:")
                .append("\nCliente: ").append(nomeCliente)
                .append("\nFuncionário: ").append(nomeFuncionario)
                .append("\nMarca: ").append(marca)
                .append("\nModelo: ").append(modelo)
                .append("\nValor Venda: ").append(valorVenda)
                .append('\n');
        return sb.toString();
    }
}
