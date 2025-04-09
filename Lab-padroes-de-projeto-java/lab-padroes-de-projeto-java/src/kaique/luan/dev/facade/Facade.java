package kaique.luan.dev.facade;

import kaique.luan.dev.facade.subSistema1.crm.CrmService;
import kaique.luan.dev.facade.subSistema2.cep.CepApi;

public class Facade {

    private final static Facade instance = new Facade();

    private Facade() {
        super();
    }

    public static Facade getInstance() {
        return instance;
    }

    public void migrarCliente(String nome, String cep) {
        String cidade = CepApi.getInstance().recuperarCidade(cep);
        String estado = CepApi.getInstance().recuperarEstado(cep);

        CrmService.gravarCliente(nome, cep, estado, cidade);
    }
}
