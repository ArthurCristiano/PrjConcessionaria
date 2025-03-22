package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.exception.DataAccessException;
import org.example.model.Venda;
import org.example.vo.RelatorioValorVendidoFunc;
import org.example.vo.RelatorioVendasPeriodo;

import java.time.LocalDate;
import java.util.List;

public class VendaDao{
    private EntityManager em;

    public VendaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Venda venda) {
        try {
            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new DataAccessException("Erro ao cadastrar a entidade: " + venda.getClass().getSimpleName(), e);
        }
    }

    public Venda buscarPorId(Long id) {
        try {
            return em.find(Venda.class , id);
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar a venda com ID: " + id, e);
        }
    }

    public void remover(Venda venda) {
        try {
            em.getTransaction().begin();
            em.remove(venda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DataAccessException("Erro ao remover a venda com ID: " + venda.getClass().getSimpleName(), e);
        }
    }

    public List<RelatorioVendasPeriodo> relatorioVendasPeriodo(LocalDate dataIni, LocalDate dataFim) {
        try {
            String sqlRel = " Select f.nomeFuncionario, " +
                            " c.nomeCliente, " +
                            " v.marca, " +
                            " v.modelo, " +
                            " cv.valorVenda " +
                            " from Venda cv " +
                            " join cv.cliente c " +
                            " join cv.funcionario f " +
                            " join cv.veiculo v " +
                            " WHERE cv.dataInc BETWEEN :dataIni AND :dataFim ";
            return em.createQuery(sqlRel, RelatorioVendasPeriodo.class)
                .setParameter("dataIni", dataIni)
                .setParameter("dataFim", dataFim)
                .getResultList();
        }  catch (Exception e) {
             throw new DataAccessException("Erro ao retornar o relatório de vendas", e);
        }
    }

    public List<RelatorioValorVendidoFunc> relatorioValorVendidoFunc(Long IdFuncionario){
        try {
            String sqlRel = " Select f.id, " +
                            " f.nomeFuncionario, " +
                            " sum(v.valorVenda) " +
                            " from Venda v " +
                            " join v.funcionario f " +
                            " where v.funcionario.id = :IdFuncionario " +
                            " group by f.id ";
            return em.createQuery(sqlRel, RelatorioValorVendidoFunc.class)
                    .setParameter("IdFuncionario", IdFuncionario)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao retornar o relatório", e);
        }
    }
}