package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cadCliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nomeCliente;
    private String docCliente;

    public Cliente(String nomeCliente, String docCliente) {
        this.nomeCliente = nomeCliente;
        this.docCliente = docCliente;
    }

    public Cliente() {}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNomeCliente(){
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }

    public String getDocCliente(){
        return docCliente;
    }

    public void setDocCliente(String docCliente){
        this.docCliente = docCliente;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            sb.append("Cliente Código: ").append(id)
                .append("\nNome: ").append(nomeCliente)
                .append("\nDocumento: ").append(docCliente)
                .append('\n');
        return sb.toString();
    }
}