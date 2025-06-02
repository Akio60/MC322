import java.util.ArrayList;

public class CentralComunicacao {
    private static CentralComunicacao instancia = new CentralComunicacao();
    private final ArrayList<String> mensagens = new ArrayList<>();

    private CentralComunicacao() { // Garantir que a classe seja um singleton
    }

    public static CentralComunicacao getInstancia(){
        return instancia;
    }

    public void registrarMensagem(String remetente, String msg) {
        mensagens.add("[" + remetente + "]: " + msg);
    }

    public void exibirMensagens() {
        System.out.println("=== Histórico de Mensagens ===");
        for (String mensagem : mensagens) {
            System.out.println(mensagem);
        }
    }

    public ArrayList<String> getMensagens() {
        return mensagens;
    }
}
