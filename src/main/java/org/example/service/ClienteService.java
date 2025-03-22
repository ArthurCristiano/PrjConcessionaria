package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.dao.ClienteDao;
import org.example.model.Cliente;
import org.example.model.Funcionario;

import java.util.List;

public class ClienteService {
    private ClienteDao clienteDao;

    public ClienteService(EntityManager em){
        this.clienteDao = new ClienteDao(em);
    }

    public void inserir(Cliente cliente){
        clienteDao.cadastrar(cliente);
    }

    public void alterar(Cliente cliente){
        clienteDao.atualizar(cliente);
    }

    public void excluir(Cliente cliente){
        clienteDao.remover(cliente);
    }

    public Cliente buscarClientesPorId(long id){
        return clienteDao.buscarPorId(id);
    }

    public List<Cliente> buscarTodosOsClientes(){
        return clienteDao.buscarTodos();
    }

    public Cliente buscarClientePorDocumento(String documento) {
        return clienteDao.buscarPorDocumento(documento);
    }
}
