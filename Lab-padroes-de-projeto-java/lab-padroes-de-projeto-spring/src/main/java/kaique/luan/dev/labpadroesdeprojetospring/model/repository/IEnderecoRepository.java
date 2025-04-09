package kaique.luan.dev.labpadroesdeprojetospring.model.repository;

import kaique.luan.dev.labpadroesdeprojetospring.model.domain.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnderecoRepository extends CrudRepository<Endereco, Long> {
}
