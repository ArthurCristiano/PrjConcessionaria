package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.exception.DataAccessException;
import org.example.model.Funcionario;
import org.example.vo.RelatorioVendasPeriodo;

import java.util.List;

public class FuncionarioDao extends GenericDao<Funcionario> {
    private EntityManager em;

    public FuncionarioDao(EntityManager em) {
        super(em, Funcionario.class);
        if (em == null) {
            throw new IllegalArgumentException("EntityManager nulo!");
        }
        this.em = em;
    }

    public Funcionario buscarPorDocumento(String documento) {
        try {
            String sql = "SELECT f FROM Funcionario f WHERE f.docFuncionario = :documento";
            return em.createQuery(sql, Funcionario.class)
                    .setParameter("documento", documento)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar o documento: " + documento, e);
        }
    }
}