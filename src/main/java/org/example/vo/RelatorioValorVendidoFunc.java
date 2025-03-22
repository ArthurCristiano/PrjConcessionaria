package org.example.vo;

public class RelatorioValorVendidoFunc {

    private Long Id;
    private String nomeFuncionario;
    private Double valorVendido;

    public RelatorioValorVendidoFunc(Long Id, String nomeFuncionario, Double valorVendido) {
        this.Id = Id;
        this.nomeFuncionario = nomeFuncionario;
        this.valorVendido = valorVendido;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nRelatório de Valor vendido: ")
                .append("\nId: ").append(Id)
                .append("\nFuncionário: ").append(nomeFuncionario)
                .append("\nValor vendido: ").append(valorVendido)
                .append('\n');
        return sb.toString();
    }

}
