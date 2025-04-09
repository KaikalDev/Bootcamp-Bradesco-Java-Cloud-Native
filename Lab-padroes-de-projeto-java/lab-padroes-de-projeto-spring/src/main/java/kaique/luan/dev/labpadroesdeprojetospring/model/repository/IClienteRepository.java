package kaique.luan.dev.labpadroesdeprojetospring.model.repository;

import kaique.luan.dev.labpadroesdeprojetospring.model.domain.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends CrudRepository<Cliente, Long> {
}
