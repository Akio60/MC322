//Interface Comunicavel
//Define as operações de comunicação entre robôs.

public interface Comunicavel {
    void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException;
    void receberMensagem(String mensagem) throws RoboDesligadoException;
}