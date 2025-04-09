package kaique.luan.dev.facade.subSistema1.crm;

public class CrmService {

    private CrmService() {
        super();
    }

    public static void gravarCliente(String nome, String cep, String estado, String cidade){
        System.out.println("Cliente salvo no sistema de CRM:"
                + "\nNome: " + nome
                + "\nCEP: " + cep
                + "\nEstado: " + estado
                + "\nCidade: " + cidade);
    }

}
