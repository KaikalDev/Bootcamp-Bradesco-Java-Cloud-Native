package kaique.luan.dev.labpadroesdeprojetospring.service;

import kaique.luan.dev.labpadroesdeprojetospring.model.domain.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void remover(Long id);

}
