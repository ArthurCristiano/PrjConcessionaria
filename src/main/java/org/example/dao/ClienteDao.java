package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.exception.DataAccessException;
import org.example.model.Cliente;

public class ClienteDao extends GenericDao<Cliente> {
     private EntityManager em;

    public ClienteDao(EntityManager em) {
        super(em, Cliente.class);
        if (em == null) {
            throw new IllegalArgumentException("EntityManager nulo!");
        }
        this.em = em;
    }

    public Cliente buscarPorDocumento(String documento) {
        try {
            String sql = "SELECT f FROM Cliente f WHERE f.docCliente = :documento";
            return em.createQuery(sql, Cliente.class)
                    .setParameter("documento", documento)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar o documento: " + documento, e);
        }
    }
}