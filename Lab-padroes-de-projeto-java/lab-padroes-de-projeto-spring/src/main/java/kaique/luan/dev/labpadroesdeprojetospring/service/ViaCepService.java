package kaique.luan.dev.labpadroesdeprojetospring.service;

import kaique.luan.dev.labpadroesdeprojetospring.model.domain.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping(value = "/{cep}/json/")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
