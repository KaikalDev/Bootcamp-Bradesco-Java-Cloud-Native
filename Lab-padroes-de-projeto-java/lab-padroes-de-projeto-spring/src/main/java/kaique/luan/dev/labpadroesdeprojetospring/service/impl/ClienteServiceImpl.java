package kaique.luan.dev.labpadroesdeprojetospring.service.impl;

import kaique.luan.dev.labpadroesdeprojetospring.model.domain.Cliente;
import kaique.luan.dev.labpadroesdeprojetospring.model.domain.Endereco;
import kaique.luan.dev.labpadroesdeprojetospring.model.repository.IClienteRepository;
import kaique.luan.dev.labpadroesdeprojetospring.model.repository.IEnderecoRepository;
import kaique.luan.dev.labpadroesdeprojetospring.service.ClienteService;
import kaique.luan.dev.labpadroesdeprojetospring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IEnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public List<Cliente> buscarTodos() {
        Iterable<Cliente> iterable = clienteRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void remover(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(Long.valueOf(cep)).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }
}
