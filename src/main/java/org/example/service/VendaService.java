package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.dao.VendaDao;
import org.example.model.Venda;
import org.example.vo.RelatorioValorVendidoFunc;
import org.example.vo.RelatorioVendasPeriodo;

import java.time.LocalDate;
import java.util.List;

public class VendaService {
    private VendaDao vendaDao;

    public VendaService(EntityManager em){
        this.vendaDao = new VendaDao(em);
    }

    public void inserir(Venda venda){
        vendaDao.cadastrar(venda);
    }

    public void excluir(Venda venda){
        vendaDao.remover(venda);
    }

    public Venda buscarVendaPorId(long id){
        return vendaDao.buscarPorId(id);
    }

    public List<RelatorioVendasPeriodo> retornaRelatorioDeVendas(LocalDate dataIni, LocalDate dataFim){
        return this.vendaDao.relatorioVendasPeriodo(dataIni, dataFim);
    }

    public List<RelatorioValorVendidoFunc> retornaRelatorioVendidoFunc(Long IdFuncionario){
        return this.vendaDao.relatorioValorVendidoFunc(IdFuncionario);
    }
}
