package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Veiculo;

public class VeiculoDao extends GenericDao<Veiculo>{
    private EntityManager em;

    public VeiculoDao(EntityManager em) {
        super(em, Veiculo.class);
    }
}