package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.dao.VeiculoDao;
import org.example.model.Veiculo;

import java.util.List;

public class VeiculoService {
    private VeiculoDao veiculoDao;

    public VeiculoService(EntityManager em){
        this.veiculoDao = new VeiculoDao(em);
    }

    public void inserir(Veiculo veiculo){
        veiculoDao.cadastrar(veiculo);
    }

    public void alterar(Veiculo veiculo){
        veiculoDao.atualizar(veiculo);
    }

    public void excluir(Veiculo veiculo){
        veiculoDao.remover(veiculo);
    }

    public Veiculo buscarVeiculoPorId(long id){
        return veiculoDao.buscarPorId(id);
    }

    public List<Veiculo> buscarTodosOsVeiculos(){
        return veiculoDao.buscarTodos();
    }
}
