package kaique.luan.dev.facade.subSistema2.cep;

public class CepApi {

    private final static CepApi instance = new CepApi();

    private CepApi() {
        super();
    }

    public static CepApi getInstance() {
        return instance;
    }

    public String recuperarCidade(String cep) {
        return "João Pessoa";
    }

    public String recuperarEstado(String cep) {
        return "PB";
    }

}
