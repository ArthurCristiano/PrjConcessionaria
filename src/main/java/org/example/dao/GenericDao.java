package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.exception.DataAccessException;

import java.util.List;

public abstract class GenericDao<T> {
    protected EntityManager em;
    private Class<T> entityClass;

    public GenericDao(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public void cadastrar(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new DataAccessException("Erro ao cadastrar a entidade: " + entity.getClass().getSimpleName(), e);
        }
    }

    public void atualizar(T entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DataAccessException("Erro ao atualizar a entidade: " + entity.getClass().getSimpleName(), e);
        }
    }

    public void remover(T entity) {
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DataAccessException("Erro ao atualizar a entidade: " + entity.getClass().getSimpleName(), e);
        }
    }

    public T buscarPorId(Long id) {
        try {
            return em.find(entityClass, id);
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar o id: "+ id +" da entidade: "+ entityClass.getSimpleName(), e);
        }
    }

    public List<T> buscarTodos() {
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";

            return em.createQuery(jpql, entityClass).getResultList();

        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar todos de: "+ entityClass.getSimpleName(), e);
        }
    }
}
